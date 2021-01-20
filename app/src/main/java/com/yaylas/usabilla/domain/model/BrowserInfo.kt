package com.yaylas.usabilla.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BrowserInfo(
    var name: String,
    var version: String,
    var platform: String
) : Parcelable