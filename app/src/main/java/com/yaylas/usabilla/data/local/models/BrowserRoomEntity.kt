package com.yaylas.usabilla.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "browsers", primaryKeys = ["platform", "browser", "version"])
class BrowserRoomEntity (
    @ColumnInfo(name = "platform")
    var platform: String,

    @ColumnInfo(name = "browser")
    var browser: String,

    @ColumnInfo(name = "version")
    var version: String
)