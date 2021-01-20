package com.yaylas.usabilla.data.network.datasource

import com.yaylas.usabilla.data.local.models.FeedbackRoomEntity
import com.yaylas.usabilla.domain.model.LocationAddress

interface NetworkDataSource {

    suspend fun get(): List<FeedbackRoomEntity>

    suspend fun getGeoLocation(latitude: Double, longitude: Double): LocationAddress
}