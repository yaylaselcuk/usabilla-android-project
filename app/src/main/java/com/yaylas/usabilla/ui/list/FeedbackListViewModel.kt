package com.yaylas.usabilla.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaylas.usabilla.domain.DataState
import com.yaylas.usabilla.domain.interactors.FilterAndSortFeedbacks
import com.yaylas.usabilla.domain.interactors.GetFeedbacksFromNetwork
import com.yaylas.usabilla.domain.model.Feedback
import com.yaylas.usabilla.util.ActionLiveData
import com.yaylas.usabilla.util.filter.Filter
import com.yaylas.usabilla.util.sort.SortType
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ActivityScoped
@ExperimentalCoroutinesApi
class FeedbackListViewModel @ViewModelInject constructor(
    private val getFeedbacksFromNetwork: GetFeedbacksFromNetwork,
    private val filterAndSortFeedbacks: FilterAndSortFeedbacks
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Feedback>>> = MutableLiveData()

    val clearFilterLiveData = ActionLiveData()

    val filter = Filter()

    var sort: SortType = SortType.TimeNewToOld

    val dataState: LiveData<DataState<List<Feedback>>>
        get() = _dataState

    fun setStateEvent(stateEvent: ListStateEvent) {
        viewModelScope.launch {
            when (stateEvent) {
                is ListStateEvent.GetFeedbacksEvent -> {
                    getFeedbacksFromNetwork.execute().onEach { ds ->
                        _dataState.value = ds
                    }.launchIn(viewModelScope)
                }
                is ListStateEvent.FilterAndSortEvent -> {
                    filterAndSortFeedbacks.execute(filter, sort)
                        .onEach { ds ->
                            _dataState.value = ds
                        }.launchIn(viewModelScope)
                }
            }
        }
    }

}

sealed class ListStateEvent {
    object GetFeedbacksEvent : ListStateEvent()
    object FilterAndSortEvent : ListStateEvent()
}