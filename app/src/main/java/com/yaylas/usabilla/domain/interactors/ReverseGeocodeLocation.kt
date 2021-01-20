package com.yaylas.usabilla.domain.interactors

import android.location.Geocoder
import com.yaylas.usabilla.domain.DataState
import com.yaylas.usabilla.domain.model.LocationAddress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class ReverseGeocodeLocation @Inject constructor(private val geocoder: Geocoder) {

    suspend fun execute(latitude: Double, longitude: Double): Flow<DataState<LocationAddress>> =
        flow {
            emit(DataState.Loading)
            try {
                val addresses = geocoder.getFromLocation(latitude, longitude, 1)
                val result = LocationAddress(addresses[0].countryName, addresses[0].adminArea)
                emit(DataState.Success(result))
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
}