package com.yaylas.usabilla.di

import android.content.Context
import androidx.room.Room
import com.yaylas.usabilla.data.local.FeedbackDaoService
import com.yaylas.usabilla.data.local.FeedbackDaoServiceImpl
import com.yaylas.usabilla.data.local.database.FeedbackDao
import com.yaylas.usabilla.data.local.database.FeedbackDatabase
import com.yaylas.usabilla.data.local.datasource.LocalDataSource
import com.yaylas.usabilla.data.local.datasource.LocalDataSourceImpl
import com.yaylas.usabilla.data.local.mappers.BrowserEntityMapper
import com.yaylas.usabilla.data.local.mappers.RoomEntityMapper
import com.yaylas.usabilla.data.local.models.FeedbackRoomEntity
import com.yaylas.usabilla.data.network.mappers.NetworkMapper
import com.yaylas.usabilla.domain.EntityMapper
import com.yaylas.usabilla.domain.model.Feedback
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object LocalDataModule {

    @Singleton
    @Provides
    fun provideRoomEntityMapper(): EntityMapper<FeedbackRoomEntity, Feedback> {
        return RoomEntityMapper()
    }

    @Singleton
    @Provides
    fun provideFeedbackDb(@ApplicationContext context: Context): FeedbackDatabase {
        return Room
            .databaseBuilder(
                context,
                FeedbackDatabase::class.java,
                FeedbackDatabase.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideFeedbackDAO(feedbackDatabase: FeedbackDatabase): FeedbackDao {
        return feedbackDatabase.feedbackDao()
    }

    @Singleton
    @Provides
    fun provideFeedbackDaoService(
        feedbackDao: FeedbackDao
    ): FeedbackDaoService {
        return FeedbackDaoServiceImpl(feedbackDao)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(
        feedbackDaoService: FeedbackDaoService,
        browserEntityMapper: BrowserEntityMapper,
        roomEntityMapper: RoomEntityMapper
    ): LocalDataSource {
        return LocalDataSourceImpl(feedbackDaoService, browserEntityMapper, roomEntityMapper)
    }

}

























