package com.yaylas.usabilla.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaylas.usabilla.domain.DataState
import com.yaylas.usabilla.domain.interactors.ReverseGeocodeLocation
import com.yaylas.usabilla.domain.model.LocationAddress
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(
    private val reverseGeocodeLocation: ReverseGeocodeLocation
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<LocationAddress>> = MutableLiveData()

    val dataState: LiveData<DataState<LocationAddress>>
        get() = _dataState

    fun setStateEvent(stateEvent: DetailStateEvent) {
        viewModelScope.launch {
            when (stateEvent) {
                is DetailStateEvent.ReverseGeoCode -> {
                    reverseGeocodeLocation.execute(stateEvent.lat, stateEvent.lon).onEach { ds ->
                        _dataState.value = ds
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

}

sealed class DetailStateEvent {
    class ReverseGeoCode(val lat: Double, val lon: Double) : DetailStateEvent()
}
