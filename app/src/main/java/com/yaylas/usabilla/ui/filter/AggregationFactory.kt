package com.yaylas.usabilla.ui.filter

import android.content.Context
import com.yaylas.usabilla.R
import com.yaylas.usabilla.domain.model.FeedbackStatus
import com.yaylas.usabilla.domain.model.FeedbackType
import com.yaylas.usabilla.util.Util
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class AggregationFactory @Inject constructor(@ActivityContext private val context: Context) {
    fun createStatusAggregations() : ArrayList<AggregationItem<FeedbackStatus>> {
        val items = ArrayList<AggregationItem<FeedbackStatus>>()
        items.add(AggregationItem(context.getString(R.string.all_title), null))
        items.add(AggregationItem(context.getString(R.string.new_title), FeedbackStatus.New))
        items.add(AggregationItem(context.getString(R.string.read_title), FeedbackStatus.Read))
        return items
    }
    fun createTypeAggregations(): ArrayList<AggregationItem<FeedbackType>> {
        val items = ArrayList<AggregationItem<FeedbackType>>()
        items.add(AggregationItem(context.getString(R.string.all_title), null))
        items.add(
            AggregationItem(
                context.getString(Util.getFeedbackTypeTitle(FeedbackType.Bug)),
                FeedbackType.Bug
            )
        )
        items.add(
            AggregationItem(
                context.getString(Util.getFeedbackTypeTitle(FeedbackType.Suggestion)),
                FeedbackType.Suggestion
            )
        )
        items.add(
            AggregationItem(
                context.getString(Util.getFeedbackTypeTitle(FeedbackType.Question)),
                FeedbackType.Question
            )
        )
        items.add(
            AggregationItem(
                context.getString(Util.getFeedbackTypeTitle(FeedbackType.Compliment)),
                FeedbackType.Compliment
            )
        )
        return items
    }

    fun createRatingAggregations(): ArrayList<AggregationItem<Int>> {
        return getOneToFiveAggregations()
    }

    fun createPerformanceAggregations(): ArrayList<AggregationItem<Int>> {
        return getOneToFiveAggregations()
    }

    fun createStarredAggregations(): ArrayList<AggregationItem<Boolean>> {
        val items = ArrayList<AggregationItem<Boolean>>()
        items.add(AggregationItem(context.getString(R.string.all_title), null))
        items.add(AggregationItem(context.getString(R.string.yes_title), true))
        items.add(AggregationItem(context.getString(R.string.no_title), false))
        return items
    }

    fun createStringAggregations(strings: List<String>): ArrayList<AggregationItem<String>> {
        val items = ArrayList<AggregationItem<String>>()
        items.add(AggregationItem(context.getString(R.string.all_title), null))
        for (s in strings) {
            items.add(AggregationItem(s, s))
        }
        return items
    }

    private fun getOneToFiveAggregations(): ArrayList<AggregationItem<Int>> {
        val items = ArrayList<AggregationItem<Int>>()
        items.add(AggregationItem(context.getString(R.string.all_title), null))
        items.add(AggregationItem("1", 1))
        items.add(AggregationItem("2", 2))
        items.add(AggregationItem("3", 3))
        items.add(AggregationItem("4", 4))
        items.add(AggregationItem("5", 5))
        return items
    }
}