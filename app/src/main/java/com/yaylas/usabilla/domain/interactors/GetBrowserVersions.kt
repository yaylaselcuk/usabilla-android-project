package com.yaylas.usabilla.domain.interactors

import com.yaylas.usabilla.data.local.datasource.LocalDataSource
import com.yaylas.usabilla.domain.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetBrowserVersions @Inject constructor(private val localDataSource: LocalDataSource) {

    suspend fun execute(): Flow<DataState<List<String>>> = flow {
        emit(DataState.Loading)
        try {
            val items = localDataSource.getBrowserVersions()
            emit(DataState.Success(items))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}