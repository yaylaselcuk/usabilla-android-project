package com.yaylas.usabilla.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feedbacks")
class FeedbackRoomEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "created_time")
    var createdAt: Long,

    @ColumnInfo(name = "latitude")
    var latitude: Double,

    @ColumnInfo(name = "longitude")
    var longitude: Double,

    @ColumnInfo(name = "city")
    var city: String,

    @ColumnInfo(name = "country")
    var country: String,

    @ColumnInfo(name = "browser")
    var browser: String,

    @ColumnInfo(name = "browser_version")
    var browserVersion: String,

    @ColumnInfo(name = "platform")
    var platform: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "status")
    var status: String,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "starred")
    var starred: Boolean,

    @ColumnInfo(name = "rating")
    var rating: Int,

    @ColumnInfo(name = "performance")
    var performance: Int,

    @ColumnInfo(name = "html_snippet")
    var htmlSnippet: String,

    @ColumnInfo(name = "labels")
    var labels: ArrayList<String>,

    @ColumnInfo(name = "ip_address")
    var ipAddress: String,

    @ColumnInfo(name = "comment")
    var comment: String,

    @ColumnInfo(name = "screenshot_image")
    var screenshotImage : String,

    @ColumnInfo(name = "full_image")
    var fullImage : String,

    @ColumnInfo(name = "cropped_image")
    var croppedImage : String,

    @ColumnInfo(name = "no_context_image")
    var noContextImage : String,

    @ColumnInfo(name = "thumbnail_image")
    var thumbnailImage : String,

    @ColumnInfo(name = "grid_image")
    var gridImage : String,

    @ColumnInfo(name = "list_image")
    var listImage : String,

    @ColumnInfo(name = "detail_image")
    var detailImage : String

    )
