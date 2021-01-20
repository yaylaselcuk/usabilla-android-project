package com.yaylas.usabilla.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImagesInfo (
    var screenshot : String,
    var fullImage : String,
    var cropped : String,
    var noContext : String,
    var thumbnail : String,
    var grid : String,
    var list : String,
    var detail : String
) : Parcelable