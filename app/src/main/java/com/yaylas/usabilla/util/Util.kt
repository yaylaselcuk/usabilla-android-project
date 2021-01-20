package com.yaylas.usabilla.util

import com.yaylas.usabilla.R
import com.yaylas.usabilla.domain.model.FeedbackType
import com.yaylas.usabilla.util.sort.SortType

object Util {
    fun getFeedbackTypeIconResId(type: FeedbackType): Int {
        return when (type) {
            FeedbackType.Suggestion -> R.drawable.ic_suggestion
            FeedbackType.Question -> R.drawable.ic_question
            FeedbackType.Bug -> R.drawable.ic_bug
            FeedbackType.Compliment -> R.drawable.ic_compliment
            else -> 0
        }
    }

    fun getFeedbackTypeColorResId(type: FeedbackType): Int {
        return when (type) {
            FeedbackType.Suggestion -> R.color.suggestion_color
            FeedbackType.Question -> R.color.question_color
            FeedbackType.Bug -> R.color.bug_color
            FeedbackType.Compliment -> R.color.compliment_color
            else -> 0
        }
    }

    fun getFeedbackTypeTitle(type: FeedbackType): Int {
        return when (type) {
            FeedbackType.Suggestion -> R.string.suggestion_title
            FeedbackType.Question -> R.string.question_title
            FeedbackType.Bug -> R.string.bug_title
            FeedbackType.Compliment -> R.string.compliment_title
            else -> 0
        }
    }

    fun getSortTitle(sortType: SortType): Int {
        return when (sortType) {
            SortType.TimeNewToOld -> R.string.time_new_to_old_title
            SortType.TimeOldToNew -> R.string.time_old_to_new_title
            SortType.RatingIncreasing -> R.string.rating_increasing_title
            SortType.RatingDecreasing -> R.string.rating_decreasing_title
            SortType.PerformanceIncreasing -> R.string.performance_decreasing_title
            SortType.PerformanceDecreasing -> R.string.performance_increasing_title
        }
    }
}