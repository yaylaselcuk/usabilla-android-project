package com.yaylas.usabilla.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FeedbackInfo(
    var feedbackType: FeedbackType,
    var rating : Int,
    var performance : String,
    var comment : String,
    var labels : String
) : Parcelable
