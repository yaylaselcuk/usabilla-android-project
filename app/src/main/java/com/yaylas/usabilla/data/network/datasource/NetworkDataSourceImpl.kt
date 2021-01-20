package com.yaylas.usabilla.data.network.datasource

import com.yaylas.usabilla.data.local.models.FeedbackRoomEntity
import com.yaylas.usabilla.data.network.FeedbackRetrofitService
import com.yaylas.usabilla.data.network.mappers.NetworkMapper

class NetworkDataSourceImpl
constructor(
    private val feedbackRetrofitService: FeedbackRetrofitService,
    private val networkMapper: NetworkMapper
) : NetworkDataSource {

    override suspend fun get(): List<FeedbackRoomEntity> {
        return networkMapper.mapFromEntityList(feedbackRetrofitService.get())
    }

}