package com.yaylas.usabilla.data.network

import com.yaylas.usabilla.data.network.models.FeedbackDTO
import com.yaylas.usabilla.data.network.retrofit.FeedbackRetrofit
import javax.inject.Inject

class FeedbackRetrofitServiceImpl @Inject constructor(private val feedbackRetrofit: FeedbackRetrofit) :
    FeedbackRetrofitService {
    override suspend fun get(): List<FeedbackDTO> {
        return feedbackRetrofit.get(VALUE_ALT, VALUE_TOKEN).items
    }

    companion object {
        private const val VALUE_ALT: String = "media"
        private const val VALUE_TOKEN: String = "9f4c880d-1a6b-4663-8c2c-c13c552243d3"
    }
}