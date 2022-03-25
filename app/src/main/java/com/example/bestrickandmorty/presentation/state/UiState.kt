package com.example.bestrickandmorty.presentation.state

sealed class UiState<D> {

    class Loading<D> : UiState<D>()
    class Error<D>(val response: String) : UiState<D>()
    class Success<D>(val data: D) : UiState<D>()

}