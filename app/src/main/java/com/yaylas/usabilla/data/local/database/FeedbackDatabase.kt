package com.yaylas.usabilla.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yaylas.usabilla.data.local.models.BrowserRoomEntity
import com.yaylas.usabilla.data.local.models.FeedbackRoomEntity

@Database(entities = [FeedbackRoomEntity::class, BrowserRoomEntity::class], version = 1)
@TypeConverters(value = [FeedbackTypeConverters::class])
abstract class FeedbackDatabase : RoomDatabase() {

    abstract fun feedbackDao(): FeedbackDao

    companion object {
        const val DATABASE_NAME: String = "feedbacks_db"
    }


}