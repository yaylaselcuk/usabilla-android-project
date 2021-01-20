package com.yaylas.usabilla.domain.interactors

import com.yaylas.usabilla.data.local.datasource.LocalDataSource
import com.yaylas.usabilla.domain.DataState
import com.yaylas.usabilla.domain.model.Feedback
import com.yaylas.usabilla.util.filter.Filter
import com.yaylas.usabilla.util.sort.SortType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FilterAndSortFeedbacks @Inject constructor(private val localDataSource: LocalDataSource) {

    suspend fun execute(filter: Filter, sortType: SortType): Flow<DataState<List<Feedback>>> = flow {
        emit(DataState.Loading)
        try {
            val feedbacks = localDataSource.filter(filter, sortType)
            emit(DataState.Success(feedbacks))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}