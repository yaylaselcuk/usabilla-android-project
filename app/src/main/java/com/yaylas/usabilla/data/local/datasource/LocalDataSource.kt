package com.yaylas.usabilla.data.local.datasource

import com.yaylas.usabilla.data.local.models.FeedbackRoomEntity
import com.yaylas.usabilla.domain.model.Feedback
import com.yaylas.usabilla.util.filter.Filter
import com.yaylas.usabilla.util.sort.SortType

interface LocalDataSource {
    suspend fun insert(feedbackRoomEntity: FeedbackRoomEntity): Long

    suspend fun insertList(feedbackRoomEntities: List<FeedbackRoomEntity>)

    suspend fun getAllFeedbacks(): List<Feedback>

    suspend fun filter(filter: Filter, sortType: SortType): List<Feedback>

    suspend fun getPlatforms(): List<String>

    suspend fun getBrowserNames(): List<String>

    suspend fun getBrowserVersions(): List<String>
}