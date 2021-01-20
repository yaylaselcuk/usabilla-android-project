package com.yaylas.usabilla.util.sort

sealed class SortType {
    object TimeNewToOld : SortType()
    object TimeOldToNew : SortType()
    object RatingIncreasing : SortType()
    object RatingDecreasing : SortType()
    object PerformanceIncreasing : SortType()
    object PerformanceDecreasing : SortType()
}