package com.yaylas.usabilla.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.gms.maps.MapsInitializer
import com.yaylas.usabilla.R
import com.yaylas.usabilla.databinding.ActivityFeedbackListBinding
import com.yaylas.usabilla.domain.DataState
import com.yaylas.usabilla.domain.model.Feedback
import com.yaylas.usabilla.ui.detail.DetailActivity
import com.yaylas.usabilla.ui.filter.FilterFragment
import com.yaylas.usabilla.ui.sort.SortFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class FeedbackListActivity : AppCompatActivity() {

    lateinit var binding: ActivityFeedbackListBinding

    private val viewModel: FeedbackListViewModel by viewModels()

    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapsInitializer.initialize(this)
        binding = ActivityFeedbackListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        subscribeObservers()
        setUpViewsAndGetData()
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, { dataState ->
            when (dataState) {
                is DataState.Success<List<Feedback>> -> dataReceived(dataState.data)
                is DataState.Error -> errorOccurred(dataState.exception.message ?: "Unknown Error")
                is DataState.Loading -> loadingData()
            }
        })
    }
    private fun setUpViewsAndGetData() {
        setUpRecyclerView()
        recyclerView.isNestedScrollingEnabled = false
        swipeRefreshLayout.setOnRefreshListener { refresh() }
        viewModel.setStateEvent(ListStateEvent.GetFeedbacksEvent)
    }

    private fun dataReceived(feedbackList: List<Feedback>) {
        hideProgressBar()
        swipeRefreshLayout.isEnabled = true
        showFeedbackData(feedbackList)
    }

    private fun loadingData() {
        swipeRefreshLayout.isEnabled = false
        tvError.visibility = View.GONE
        showProgressBar()
    }

    private fun errorOccurred(message: String) {
        hideProgressBar()
        swipeRefreshLayout.isEnabled = true
        displayError(message)
    }

    private fun setUpRecyclerView() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    private fun showFeedbackData(feedbackList: List<Feedback>) {
        recyclerView.adapter = FeedbackAdapter(feedbackList, ::gotoDetail)
    }

    private fun displayError(message: String) {
        tvError.text = getString(R.string.retrieve_data_error, message)
        tvError.visibility = View.VISIBLE
    }

    private fun gotoDetail(feedback: Feedback) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.KEY_FEEDBACK_OBJECT, feedback)
        startActivity(intent)
    }


    private fun refresh() {
        swipeRefreshLayout.isRefreshing = false
        viewModel.setStateEvent(ListStateEvent.FilterAndSortEvent)
    }

    private fun showFilters() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, FilterFragment())
            .addToBackStack(null)
            .commitAllowingStateLoss()
        menu?.apply {
            getItem(0).isVisible = false
            getItem(1).isVisible = false
            getItem(2).isVisible = true
        }

    }

    private fun showSorting() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, SortFragment())
            .addToBackStack(null)
            .commitAllowingStateLoss()
        menu?.apply {
            getItem(0).isVisible = false
            getItem(1).isVisible = false
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        this.menu = menu
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter -> showFilters()
            R.id.sort -> showSorting()
            R.id.clear_filter -> viewModel.clearFilterLiveData.call()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        menu?.apply {
            getItem(0).isVisible = true
            getItem(1).isVisible = true
            getItem(2).isVisible = false
        }
        super.onBackPressed()
    }

}

@ExperimentalCoroutinesApi
private val FeedbackListActivity.swipeRefreshLayout: SwipeRefreshLayout
    get() {
        return binding.swipeRefreshLayout
    }

@ExperimentalCoroutinesApi
private val FeedbackListActivity.recyclerView: RecyclerView
    get() {
        return binding.recyclerView
    }

@ExperimentalCoroutinesApi
private val FeedbackListActivity.progressBar: ProgressBar
    get() {
        return binding.progressBar
    }

@ExperimentalCoroutinesApi
private val FeedbackListActivity.tvError: TextView
    get() {
        return binding.tvError
    }