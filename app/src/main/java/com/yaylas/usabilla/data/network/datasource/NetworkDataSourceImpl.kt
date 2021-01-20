package com.yaylas.usabilla.data.network.datasource

import android.location.Geocoder
import com.yaylas.usabilla.data.local.models.FeedbackRoomEntity
import com.yaylas.usabilla.data.network.FeedbackRetrofitService
import com.yaylas.usabilla.data.network.mappers.NetworkMapper
import com.yaylas.usabilla.domain.model.LocationAddress

class NetworkDataSourceImpl
constructor(
    private val feedbackRetrofitService: FeedbackRetrofitService,
    private val networkMapper: NetworkMapper,
    private val geocoder: Geocoder
) : NetworkDataSource {

    override suspend fun get(): List<FeedbackRoomEntity> {
        return networkMapper.mapFromEntityList(feedbackRetrofitService.get())
    }

    override suspend fun getGeoLocation(latitude: Double, longitude: Double): LocationAddress {
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        return LocationAddress(addresses[0].countryName, addresses[0].adminArea)
    }
}