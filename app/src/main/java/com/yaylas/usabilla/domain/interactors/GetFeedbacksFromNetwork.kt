package com.yaylas.usabilla.domain.interactors

import com.yaylas.usabilla.data.local.datasource.LocalDataSource
import com.yaylas.usabilla.data.network.datasource.NetworkDataSource
import com.yaylas.usabilla.domain.DataState
import com.yaylas.usabilla.domain.model.Feedback
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject


class GetFeedbacksFromNetwork
@Inject
constructor(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource
) {

    suspend fun execute(): Flow<DataState<List<Feedback>>> = flow {
        emit(DataState.Loading)
        try {
            val localData = localDataSource.getAllFeedbacks()
            if (localData.isNotEmpty()) {
                emit(DataState.Success(localData))
            } else {
                val networkFeedbacks = networkDataSource.get()
                localDataSource.insertList(networkFeedbacks)
                emit(DataState.Success(localDataSource.getAllFeedbacks()))
            }
        } catch (e : Exception) {
            emit(DataState.Error(e))
        }
    }

}