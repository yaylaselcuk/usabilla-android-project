package com.yaylas.usabilla.util

import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.yaylas.usabilla.util.filter.Filter
import com.yaylas.usabilla.util.sort.SortType

object QueryCreator {

    fun createQuery(filter: Filter, sortType: SortType = SortType.TimeNewToOld): SupportSQLiteQuery {
        val args = ArrayList<Any>()
        val queryString = "select * from feedbacks${
            convertFilterToQueryString(
                filter,
                args
            )
        }${convertSortToQueryString(sortType)}"
        return SimpleSQLiteQuery(queryString, args.toArray())
    }

    private fun convertFilterToQueryString(filter: Filter, args: ArrayList<Any>): String {
        var queryString = ""
        var hasCondition = false
        filter.type?.let {
            queryString += if (hasCondition) " and type = ?" else " where type = ?"
            args.add(Converters.feedbackTypeToString(it))
            hasCondition = true
        }

        filter.status?.let {
            queryString += if (hasCondition) " and status = ?" else " where status = ?"
            args.add(Converters.feedbackStatusToString(it))
            hasCondition = true
        }

        filter.starred?.let {
            queryString += if (hasCondition) " and starred = ?" else " where starred = ?"
            args.add(it)
            hasCondition = true
        }

        filter.rating?.let {
            queryString += if (hasCondition) " and rating = ?" else " where rating = ?"
            args.add(it)
            hasCondition = true
        }

        filter.performance?.let {
            queryString += if (hasCondition) " and performance = ?" else " where performance = ?"
            args.add(it)
            hasCondition = true
        }

        filter.browser?.let {
            queryString += if (hasCondition) " and browser = ?" else " where browser = ?"
            args.add(it)
            hasCondition = true
        }
        filter.browserVersion?.let {
            queryString += if (hasCondition) " and browser_version = ?" else " where browser_version = ?"
            args.add(it)
            hasCondition = true
        }

        filter.platform?.let {
            queryString += if (hasCondition) " and platform = ?" else " where platform = ?"
            args.add(it)
            hasCondition = true
        }
        return queryString
    }

    private fun convertSortToQueryString(sortType: SortType): String {
        return when (sortType) {
            is SortType.PerformanceDecreasing -> " order by performance desc"
            is SortType.PerformanceIncreasing -> " order by performance asc"
            is SortType.TimeNewToOld -> " order by created_time desc"
            is SortType.TimeOldToNew -> " order by created_time asc"
            is SortType.RatingDecreasing -> " order by rating desc"
            is SortType.RatingIncreasing -> " order by rating desc"
        }
    }

}