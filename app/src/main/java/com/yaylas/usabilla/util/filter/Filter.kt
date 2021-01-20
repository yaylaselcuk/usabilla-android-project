package com.yaylas.usabilla.util.filter

import androidx.annotation.IntRange
import com.yaylas.usabilla.domain.model.FeedbackStatus
import com.yaylas.usabilla.domain.model.FeedbackType

class Filter {
    var status: FeedbackStatus? = null
    var type: FeedbackType? = null
    var browser: String? = null
    var browserVersion: String? = null
    var platform: String? = null
    var starred: Boolean? = null
    @IntRange(from = 1, to = 5) var rating: Int? = null
    @IntRange(from = 1, to = 5) var performance: Int? = null
}