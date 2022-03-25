package com.example.bestrickandmorty.domain.location.model

import com.example.bestrickandmorty.domain.common.base.BaseId

data class LocationEntity(
    val created: String,
    val dimension: String,
    override val id: Int,
    val name: String,
    val residents: List<Any>,
    val type: String,
    val url: String
): BaseId