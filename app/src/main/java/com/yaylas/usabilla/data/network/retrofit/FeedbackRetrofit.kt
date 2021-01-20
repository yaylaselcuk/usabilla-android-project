package com.yaylas.usabilla.data.network.retrofit

import com.yaylas.usabilla.data.network.models.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedbackRetrofit {

    @GET("v0/b/usabilla-b396e.appspot.com/o/apidata.json")
    suspend fun get(@Query("alt") alt: String, @Query("token") token: String): ApiResponse
}