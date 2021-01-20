package com.yaylas.usabilla.ui.sort

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaylas.usabilla.R
import com.yaylas.usabilla.databinding.FragmentSortBinding
import com.yaylas.usabilla.ui.list.FeedbackListViewModel
import com.yaylas.usabilla.ui.list.ListStateEvent
import com.yaylas.usabilla.util.Util
import com.yaylas.usabilla.util.sort.SortType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SortFragment : Fragment(R.layout.fragment_sort) {

    private val activityViewModel: FeedbackListViewModel by viewModels({ requireActivity() })

    private var _binding: FragmentSortBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: SortTypesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSortBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        fillData()
    }

    private fun setUpViews() {
        setUpRecyclerView()
        binding.btnSort.setOnClickListener { applySort() }
        binding.root.setOnClickListener { requireActivity().onBackPressed() }
    }


    private fun setUpRecyclerView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

    }

    private fun fillData() {
        adapter = SortTypesAdapter(createSortItems(), activityViewModel.sort)
        binding.recyclerView.adapter = adapter
    }

    private fun applySort() {
        activityViewModel.sort = adapter.selectedType
        activityViewModel.setStateEvent(ListStateEvent.FilterAndSortEvent)
        requireActivity().onBackPressed()
    }

    private fun createSortItems(): ArrayList<SortItem> {
        val items = ArrayList<SortItem>()
        items.add(createSortItem(SortType.TimeNewToOld))
        items.add(createSortItem(SortType.TimeOldToNew))
        items.add(createSortItem(SortType.RatingDecreasing))
        items.add(createSortItem(SortType.RatingIncreasing))
        items.add(createSortItem(SortType.PerformanceDecreasing))
        items.add(createSortItem(SortType.PerformanceIncreasing))
        return items
    }

    private fun createSortItem(sortType: SortType): SortItem {
        return SortItem(sortType, getString(Util.getSortTitle(sortType)))
    }
}