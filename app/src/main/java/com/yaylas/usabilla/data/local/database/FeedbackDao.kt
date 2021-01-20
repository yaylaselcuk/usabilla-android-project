package com.yaylas.usabilla.data.local.database

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.yaylas.usabilla.data.local.models.BrowserRoomEntity
import com.yaylas.usabilla.data.local.models.FeedbackRoomEntity

@Dao
interface FeedbackDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeedback(feedbackRoomEntity: FeedbackRoomEntity): Long

    @Query("SELECT * FROM feedbacks order by created_time desc")
    suspend fun get(): List<FeedbackRoomEntity>

    @RawQuery(observedEntities = [FeedbackRoomEntity::class])
    suspend fun filterFeedbacks(query: SupportSQLiteQuery): List<FeedbackRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBrowser(browserRoomEntity: BrowserRoomEntity): Long

    @Query("SELECT platform FROM browsers group by platform order by platform asc")
    suspend fun getPlatforms(): List<String>

    @Query("SELECT browser FROM browsers group by browser order by browser asc")
    suspend fun getBrowserNames(): List<String>

    @Query("SELECT version FROM browsers group by version order by version asc")
    suspend fun getBrowserVersions(): List<String>


}