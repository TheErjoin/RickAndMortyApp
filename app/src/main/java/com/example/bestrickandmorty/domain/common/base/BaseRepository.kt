package com.example.bestrickandmorty.domain.common.base

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


abstract class BaseRepository {

    protected fun <T> doExecution(execution: suspend () -> T): Flow<BaseResult<T, String>> {
        return flow {
            emit(BaseResult.Loading(null))
            try {
                emit(BaseResult.Success(data = execution()))
            } catch (exception: Exception) {
                emit(BaseResult.Error(rawResponse = exception.localizedMessage ?: "Error data"))
            }
        }
    }

    protected fun <ValueDto : Any, Value : Any> doPaging(
        pagingSource: BasePagingSource<ValueDto, Value>,
        pageSize: Int = 10,
        prefetchDistance: Int = pageSize,
    ): Flow<PagingData<Value>> {
        return Pager(
            config = PagingConfig(
                pageSize,
                prefetchDistance,
            ),
            pagingSourceFactory = {
                pagingSource
            },
        ).flow
    }

}