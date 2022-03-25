package com.example.bestrickandmorty.domain.common.base

sealed class BaseResult<out T, out U : Any> {
    data class Success<T>(val data: T) : BaseResult<T, Nothing>()
    data class Error<U : Any>(val rawResponse: U) : BaseResult<Nothing, U>()
    data class Loading<T>(val data: T) : BaseResult<Nothing, Nothing>()
}