package com.yaylas.usabilla.data.network.models

import com.google.gson.annotations.SerializedName

data class BrowserDTO(
    @SerializedName("Browser") var browser : String?,
    @SerializedName("Version") var version : String?,
    @SerializedName("Platform") var platform : String?
)