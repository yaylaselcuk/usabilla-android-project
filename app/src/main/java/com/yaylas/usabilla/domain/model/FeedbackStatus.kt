package com.yaylas.usabilla.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class FeedbackStatus : Parcelable {
    @Parcelize object New : FeedbackStatus()
    @Parcelize object Read : FeedbackStatus()
}