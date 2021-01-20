package com.yaylas.usabilla.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserInfo(
    var email : String,
    var ipAddress : String,
    var country : String,
    var city : String,
    var latitude : Double,
    var longitude : Double
) : Parcelable
