package com.yaylas.usabilla.ui.filter

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaylas.usabilla.domain.DataState
import com.yaylas.usabilla.domain.interactors.GetBrowserNames
import com.yaylas.usabilla.domain.interactors.GetBrowserVersions
import com.yaylas.usabilla.domain.interactors.GetPlatforms
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class FilterViewModel @ViewModelInject constructor(
    private val getPlatforms: GetPlatforms,
    private val getBrowserNames: GetBrowserNames,
    private val getBrowserVersions: GetBrowserVersions
) : ViewModel() {

    private val _platformsDataState: MutableLiveData<DataState<List<String>>> = MutableLiveData()
    private val _browsersDataState: MutableLiveData<DataState<List<String>>> = MutableLiveData()
    private val _versionsDataState: MutableLiveData<DataState<List<String>>> = MutableLiveData()


    val platformsDataState: LiveData<DataState<List<String>>>
        get() = _platformsDataState
    val browsersDataState: LiveData<DataState<List<String>>>
        get() = _browsersDataState
    val versionsDataState: LiveData<DataState<List<String>>>
        get() = _versionsDataState

    fun setStateEvent(stateEvent: FilterStateEvent) {
        viewModelScope.launch {
            when (stateEvent) {
                is FilterStateEvent.GetPlatforms -> {
                    getPlatforms.execute().onEach { ds ->
                        _platformsDataState.value = ds
                    }.launchIn(viewModelScope)
                }
                is FilterStateEvent.GetBrowserNames -> {
                    getBrowserNames.execute().onEach { ds ->
                        _browsersDataState.value = ds
                    }.launchIn(viewModelScope)
                }
                is FilterStateEvent.GetBrowserVersions -> {
                    getBrowserVersions.execute().onEach { ds ->
                        _versionsDataState.value = ds
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

}

sealed class FilterStateEvent {
    object GetPlatforms : FilterStateEvent()
    object GetBrowserNames : FilterStateEvent()
    object GetBrowserVersions : FilterStateEvent()
}