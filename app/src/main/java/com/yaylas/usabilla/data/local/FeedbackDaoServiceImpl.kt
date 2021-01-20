package com.yaylas.usabilla.data.local

import androidx.sqlite.db.SupportSQLiteQuery
import com.yaylas.usabilla.data.local.database.FeedbackDao
import com.yaylas.usabilla.data.local.models.BrowserRoomEntity
import com.yaylas.usabilla.data.local.models.FeedbackRoomEntity


class FeedbackDaoServiceImpl
constructor(
    private val feedbackDao: FeedbackDao
) : FeedbackDaoService {

    override suspend fun insertFeedback(feedbackRoomEntity: FeedbackRoomEntity): Long {
        return feedbackDao.insertFeedback(feedbackRoomEntity)
    }

    override suspend fun getFeedbacks(): List<FeedbackRoomEntity> {
        return feedbackDao.get()
    }

    override suspend fun filter(query: SupportSQLiteQuery): List<FeedbackRoomEntity> {
        return feedbackDao.filterFeedbacks(query)
    }

    override suspend fun insertBrowser(browserRoomEntity: BrowserRoomEntity): Long {
        return feedbackDao.insertBrowser(browserRoomEntity)
    }

    override suspend fun getPlatforms(): List<String> {
        return feedbackDao.getPlatforms()
    }

    override suspend fun getBrowserNames(): List<String> {
        return feedbackDao.getBrowserNames()
    }

    override suspend fun getBrowserVersions(): List<String> {
        return feedbackDao.getBrowserVersions()
    }
}