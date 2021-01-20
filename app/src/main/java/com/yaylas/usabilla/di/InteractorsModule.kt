package com.yaylas.usabilla.di

import com.yaylas.usabilla.data.local.datasource.LocalDataSource
import com.yaylas.usabilla.data.network.datasource.NetworkDataSource
import com.yaylas.usabilla.domain.interactors.FilterAndSortFeedbacks
import com.yaylas.usabilla.domain.interactors.GetFeedbacksFromNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideGetFeedbacksFromNetwork(
        localDataSource: LocalDataSource,
        networkDataSource: NetworkDataSource
    ): GetFeedbacksFromNetwork {
        return GetFeedbacksFromNetwork(localDataSource, networkDataSource)
    }

    @Singleton
    @Provides
    fun provideFilterAndSortFeedbacks(
        localDataSource: LocalDataSource
    ): FilterAndSortFeedbacks {
        return FilterAndSortFeedbacks(localDataSource)
    }
}














