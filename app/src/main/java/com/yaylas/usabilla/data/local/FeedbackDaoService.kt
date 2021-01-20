package com.yaylas.usabilla.data.local

import androidx.sqlite.db.SupportSQLiteQuery
import com.yaylas.usabilla.data.local.models.BrowserRoomEntity
import com.yaylas.usabilla.data.local.models.FeedbackRoomEntity

interface FeedbackDaoService {
    suspend fun insertFeedback(feedbackRoomEntity: FeedbackRoomEntity): Long

    suspend fun getFeedbacks(): List<FeedbackRoomEntity>

    suspend fun filter(query: SupportSQLiteQuery): List<FeedbackRoomEntity>

    suspend fun insertBrowser(browserRoomEntity: BrowserRoomEntity): Long

    suspend fun getPlatforms(): List<String>

    suspend fun getBrowserNames(): List<String>

    suspend fun getBrowserVersions(): List<String>

}