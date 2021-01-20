package com.yaylas.usabilla.di

import android.location.Geocoder
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.yaylas.usabilla.data.local.models.FeedbackRoomEntity
import com.yaylas.usabilla.data.network.CustomDTODeserializer
import com.yaylas.usabilla.data.network.FeedbackRetrofitService
import com.yaylas.usabilla.data.network.FeedbackRetrofitServiceImpl
import com.yaylas.usabilla.data.network.datasource.NetworkDataSource
import com.yaylas.usabilla.data.network.datasource.NetworkDataSourceImpl
import com.yaylas.usabilla.data.network.mappers.NetworkMapper
import com.yaylas.usabilla.data.network.models.ApiResponse
import com.yaylas.usabilla.data.network.models.CustomDTO
import com.yaylas.usabilla.data.network.models.FeedbackDTO
import com.yaylas.usabilla.data.network.retrofit.FeedbackRetrofit
import com.yaylas.usabilla.domain.EntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.String
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkMapper(): EntityMapper<FeedbackDTO, FeedbackRoomEntity> {
        return NetworkMapper()
    }

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .setLenient()
            .registerTypeAdapter(CustomDTO::class.java, CustomDTODeserializer())
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://firebasestorage.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideFeedbackService(retrofit: Retrofit.Builder): FeedbackRetrofit {
        return retrofit
            .build()
            .create(FeedbackRetrofit::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitService(
        feedbackRetrofit: FeedbackRetrofit
    ): FeedbackRetrofitService {
        return FeedbackRetrofitServiceImpl(feedbackRetrofit)
    }

    @Singleton
    @Provides
    fun provideNetworkDataSource(
        feedbackRetrofitService: FeedbackRetrofitService,
        networkMapper: NetworkMapper,
        geocoder: Geocoder
    ): NetworkDataSource {
        return NetworkDataSourceImpl(feedbackRetrofitService, networkMapper, geocoder)
    }

}




















