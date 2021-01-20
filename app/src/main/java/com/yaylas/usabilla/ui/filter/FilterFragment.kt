package com.yaylas.usabilla.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yaylas.usabilla.R
import com.yaylas.usabilla.databinding.FragmentFilterBinding
import com.yaylas.usabilla.domain.DataState
import com.yaylas.usabilla.domain.model.FeedbackStatus
import com.yaylas.usabilla.domain.model.FeedbackType
import com.yaylas.usabilla.ui.list.FeedbackListViewModel
import com.yaylas.usabilla.ui.list.ListStateEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class FilterFragment : Fragment(R.layout.fragment_filter) {

    private val viewModel: FilterViewModel by viewModels()

    @Inject
    lateinit var aggregationFactory: AggregationFactory

    private val activityViewModel: FeedbackListViewModel by viewModels({ requireActivity() })

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        subscribeObservers()
        sendDataEvents()
    }

    private fun sendDataEvents() {
        viewModel.setStateEvent(FilterStateEvent.GetPlatforms)
        viewModel.setStateEvent(FilterStateEvent.GetBrowserNames)
        viewModel.setStateEvent(FilterStateEvent.GetBrowserVersions)
    }

    private fun subscribeObservers() {
        viewModel.platformsDataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<List<String>> -> setUpPlatformSpinner(dataState.data)
                is DataState.Error -> errorOccurred(dataState.exception.message)
                is DataState.Loading -> {
                }
            }
        })
        viewModel.browsersDataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<List<String>> -> setUpBrowserSpinner(dataState.data)
                is DataState.Error -> errorOccurred(dataState.exception.message)
                is DataState.Loading -> {
                }
            }
        })
        viewModel.versionsDataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<List<String>> -> setUpVersionSpinner(dataState.data)
                is DataState.Error -> errorOccurred(dataState.exception.message)
                is DataState.Loading -> {
                }
            }
        })
        activityViewModel.clearFilterLiveData.observe(viewLifecycleOwner, {
            clearAllFilters()
        })
    }


    private fun setUpViews() {
        setUpStatusSpinner()
        setUpTypeSpinner()
        setUpStarredSpinner()
        setUpRatingSpinner()
        setUpPerformanceSpinner()
        binding.btnFilter.setOnClickListener { filterAndCloseThis() }
        binding.root.setOnClickListener { requireActivity().onBackPressed() }
    }

    private fun errorOccurred(message: String?) {
        Toast.makeText(
            requireContext(),
            message ?: getString(R.string.general_error_text),
            Toast.LENGTH_SHORT
        ).show()
    }


    private fun setUpStatusSpinner() {
        val adapter = AggregationsAdapter(aggregationFactory.createStatusAggregations())
        binding.spStatus.adapter = adapter
        binding.spStatus.setSelection(adapter.getItemPosition(activityViewModel.filter.status))
    }

    private fun setUpTypeSpinner() {
        val adapter = AggregationsAdapter(aggregationFactory.createTypeAggregations())
        binding.spFeedbackType.adapter = adapter
        binding.spFeedbackType.setSelection(adapter.getItemPosition(activityViewModel.filter.type))
    }


    private fun setUpPlatformSpinner(platforms: List<String>) {
        val adapter = AggregationsAdapter(aggregationFactory.createStringAggregations(platforms))
        binding.spPlatform.adapter = adapter
        binding.spPlatform.setSelection(adapter.getItemPosition(activityViewModel.filter.platform))
    }

    private fun setUpBrowserSpinner(browsers: List<String>) {

        val adapter = AggregationsAdapter(aggregationFactory.createStringAggregations(browsers))
        binding.spBrowser.adapter = adapter
        binding.spBrowser.setSelection(adapter.getItemPosition(activityViewModel.filter.browser))
    }


    private fun setUpVersionSpinner(versions: List<String>) {
        val adapter = AggregationsAdapter(aggregationFactory.createStringAggregations(versions))
        binding.spBrowserVersion.adapter = adapter
        binding.spBrowserVersion.setSelection(adapter.getItemPosition(activityViewModel.filter.browserVersion))
    }


    private fun setUpStarredSpinner() {
        val adapter = AggregationsAdapter(aggregationFactory.createStarredAggregations())
        binding.spStarred.adapter = adapter
        binding.spStarred.setSelection(adapter.getItemPosition(activityViewModel.filter.starred))
    }

    private fun setUpRatingSpinner() {
        val adapter = AggregationsAdapter(aggregationFactory.createRatingAggregations())
        binding.spRating.adapter = adapter
        binding.spRating.setSelection(adapter.getItemPosition(activityViewModel.filter.rating))
    }

    private fun setUpPerformanceSpinner() {
        val adapter = AggregationsAdapter(aggregationFactory.createPerformanceAggregations())
        binding.spPerformance.adapter = adapter
        binding.spPerformance.setSelection(adapter.getItemPosition(activityViewModel.filter.performance))
    }

    private fun clearAllFilters() {
        binding.spBrowser.setSelection(0)
        binding.spPlatform.setSelection(0)
        binding.spBrowserVersion.setSelection(0)
        binding.spPerformance.setSelection(0)
        binding.spStarred.setSelection(0)
        binding.spFeedbackType.setSelection(0)
        binding.spStatus.setSelection(0)
        binding.spRating.setSelection(0)
    }

    private fun filterAndCloseThis() {
        with(activityViewModel.filter) {
            platform = (binding.spPlatform.selectedItem as AggregationItem<String>).associatedObject
            browser = (binding.spBrowser.selectedItem as AggregationItem<String>).associatedObject
            browserVersion =
                (binding.spBrowserVersion.selectedItem as AggregationItem<String>).associatedObject
            status =
                (binding.spStatus.selectedItem as AggregationItem<FeedbackStatus>).associatedObject
            type =
                (binding.spFeedbackType.selectedItem as AggregationItem<FeedbackType>).associatedObject
            rating = (binding.spRating.selectedItem as AggregationItem<Int>).associatedObject
            performance =
                (binding.spPerformance.selectedItem as AggregationItem<Int>).associatedObject
            starred = (binding.spStarred.selectedItem as AggregationItem<Boolean>).associatedObject
        }
        activityViewModel.setStateEvent(ListStateEvent.FilterAndSortEvent)
        requireActivity().onBackPressed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}