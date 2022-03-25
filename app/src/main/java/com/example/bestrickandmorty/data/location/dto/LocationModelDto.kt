package com.example.bestrickandmorty.data.location.dto

import com.example.bestrickandmorty.domain.location.model.LocationEntity

data class LocationModelDto(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<Any>,
    val type: String,
    val url: String
)

fun LocationModelDto.toLocation(): LocationEntity {
    return LocationEntity(
        created, dimension, id, name, residents, type, url
    )
}