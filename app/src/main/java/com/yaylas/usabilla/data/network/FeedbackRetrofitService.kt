package com.yaylas.usabilla.data.network

import com.yaylas.usabilla.data.network.models.FeedbackDTO

interface FeedbackRetrofitService {
    suspend fun get(): List<FeedbackDTO>
}