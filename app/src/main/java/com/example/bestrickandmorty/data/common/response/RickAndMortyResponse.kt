package com.example.bestrickandmorty.data.common.response

data class RickAndMortyResponse<T>(
    val info: Info,
    val results: MutableList<T>
)