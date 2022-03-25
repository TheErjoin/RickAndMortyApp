package com.example.bestrickandmorty.domain.episode.model

import com.example.bestrickandmorty.domain.common.base.BaseId

data class EpisodeEntity(
    val air_date: String,
    val characters: List<Any>,
    val created: String,
    val episode: String,
    override val id: Int,
    val name: String,
    val url: String
): BaseId