package com.yaylas.usabilla.domain.interactors

import com.yaylas.usabilla.data.network.datasource.NetworkDataSource
import com.yaylas.usabilla.domain.DataState
import com.yaylas.usabilla.domain.model.LocationAddress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class ReverseGeocodeLocation @Inject constructor(private val networkDataSource: NetworkDataSource) {

    suspend fun execute(latitude: Double, longitude: Double): Flow<DataState<LocationAddress>> =
        flow {
            emit(DataState.Loading)
            try {
                val result = networkDataSource.getGeoLocation(latitude, longitude)
                emit(DataState.Success(result))
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
}