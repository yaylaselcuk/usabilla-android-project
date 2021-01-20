package com.yaylas.usabilla.data.local.datasource

import com.yaylas.usabilla.data.local.FeedbackDaoService
import com.yaylas.usabilla.data.local.mappers.BrowserEntityMapper
import com.yaylas.usabilla.data.local.mappers.RoomEntityMapper
import com.yaylas.usabilla.data.local.models.FeedbackRoomEntity
import com.yaylas.usabilla.domain.model.Feedback
import com.yaylas.usabilla.util.QueryCreator
import com.yaylas.usabilla.util.filter.Filter
import com.yaylas.usabilla.util.sort.SortType
import javax.inject.Inject

class LocalDataSourceImpl
@Inject constructor(
    private val feedbackDaoService: FeedbackDaoService,
    private val browserEntityMapper: BrowserEntityMapper,
    private val roomEntityMapper: RoomEntityMapper
) : LocalDataSource {

    override suspend fun insert(feedbackRoomEntity: FeedbackRoomEntity): Long {
        return feedbackDaoService.insertFeedback(feedbackRoomEntity)
    }


    override suspend fun insertList(feedbackRoomEntities: List<FeedbackRoomEntity>) {
        for (feedbackRoomEntity in feedbackRoomEntities) {
            feedbackDaoService.insertFeedback(feedbackRoomEntity)
            feedbackDaoService.insertBrowser(browserEntityMapper.mapFromEntity(feedbackRoomEntity))
        }
    }

    override suspend fun getAllFeedbacks(): List<Feedback> {
        return roomEntityMapper.mapFromEntityList(feedbackDaoService.getFeedbacks())
    }

    override suspend fun filter(filter: Filter, sortType: SortType): List<Feedback> {
        val query = QueryCreator.createQuery(filter, sortType)
        return roomEntityMapper.mapFromEntityList(feedbackDaoService.filter(query))
    }

    override suspend fun getPlatforms(): List<String> = feedbackDaoService.getPlatforms()

    override suspend fun getBrowserNames(): List<String> = feedbackDaoService.getBrowserNames()

    override suspend fun getBrowserVersions(): List<String> = feedbackDaoService.getBrowserVersions()

}