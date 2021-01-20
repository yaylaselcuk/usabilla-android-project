package com.yaylas.usabilla.data.network.models

import com.google.gson.annotations.SerializedName
import com.yaylas.usabilla.data.network.models.images.ImagesDTO

data class FeedbackDTO(
    var id: String,
    var email: String?,
    var labels: ArrayList<String>?,
    var custom: CustomDTO?,
    var geo: GeoDTO?,
    @SerializedName("computed_browser") var browser: BrowserDTO?,
    @SerializedName("computed_location") var country: String?,
    var rating: Int?,
    var performance: Int?,
    var ip: String?,
    var status: String?,
    var starred: Boolean?,
    var comment: String?,
    var images : ImagesDTO?,
    @SerializedName("html_snippet") var htmlSnippet: String?,
    @SerializedName("creation_date") var createdAt: Long

)