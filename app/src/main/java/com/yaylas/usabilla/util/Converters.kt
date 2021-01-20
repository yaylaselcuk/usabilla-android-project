package com.yaylas.usabilla.util

import com.yaylas.usabilla.domain.model.FeedbackStatus
import com.yaylas.usabilla.domain.model.FeedbackType

object Converters {

    fun feedbackTypeToString(feedbackType: FeedbackType = FeedbackType.None): String {
        return when (feedbackType) {
            is FeedbackType.Suggestion -> "suggestion"
            is FeedbackType.Question -> "question"
            is FeedbackType.Bug -> "bug"
            is FeedbackType.Compliment -> "compliment"
            is FeedbackType.None -> "none"
        }
    }

    fun feedbackStatusToString(feedbackStatus: FeedbackStatus): String {
        return when (feedbackStatus) {
            is FeedbackStatus.New -> "new"
            is FeedbackStatus.Read -> "read"
        }
    }

    fun stringToFeedbackType(str: String): FeedbackType {
        return when (str) {
            "suggestion" -> FeedbackType.Suggestion
            "question" -> FeedbackType.Question
            "bug" -> FeedbackType.Bug
            "compliment" -> FeedbackType.Compliment
            else -> FeedbackType.None
        }
    }

    fun stringToFeedbackStatus(str: String): FeedbackStatus {
        return when (str) {
            "read" -> FeedbackStatus.Read
            else -> FeedbackStatus.New
        }
    }
}