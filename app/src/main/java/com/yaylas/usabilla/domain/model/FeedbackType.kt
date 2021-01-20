package com.yaylas.usabilla.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class FeedbackType : Parcelable {
    @Parcelize object None : FeedbackType()
    @Parcelize object Bug : FeedbackType()
    @Parcelize object Compliment : FeedbackType()
    @Parcelize object Suggestion : FeedbackType()
    @Parcelize object Question : FeedbackType()
}
