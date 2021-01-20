package com.yaylas.usabilla.data.network.datasource

import com.yaylas.usabilla.data.local.models.FeedbackRoomEntity

interface NetworkDataSource {

    suspend fun get(): List<FeedbackRoomEntity>
}