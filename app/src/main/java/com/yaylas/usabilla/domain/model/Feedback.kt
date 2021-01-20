package com.yaylas.usabilla.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Feedback(
    var id: String,
    var user: UserInfo,
    var status: FeedbackStatus,
    var info: FeedbackInfo,
    var browser: BrowserInfo,
    var images: ImagesInfo,
    var htmlSnippet : String,
    var creationDate : String,
    var starred : Boolean
) : Parcelable