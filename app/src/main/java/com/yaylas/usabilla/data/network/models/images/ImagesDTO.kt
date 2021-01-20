package com.yaylas.usabilla.data.network.models.images

import com.google.gson.annotations.SerializedName

data class ImagesDTO(
    var screenshot: ImageDetailDTO,
    @SerializedName("full_image") var fullImage: ImageDetailDTO,
    var cropped: ImageDetailDTO,
    @SerializedName("no_context") var noContext: ImageDetailDTO,
    var thumbnail: ImageDetailDTO,
    var grid: ImageDetailDTO,
    var list: ImageDetailDTO,
    var detail: ImageDetailDTO
)