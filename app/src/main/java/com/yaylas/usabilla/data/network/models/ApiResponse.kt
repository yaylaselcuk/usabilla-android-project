package com.yaylas.usabilla.data.network.models

data class ApiResponse(
    var items : List<FeedbackDTO>,
    var count : Int
)