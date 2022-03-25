package com.example.bestrickandmorty.data.episode.dto

import com.example.bestrickandmorty.domain.episode.model.EpisodeEntity

class EpisodeModelDto(

    val air_date: String,
    val characters: List<Any>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String

)

fun EpisodeModelDto.toEpisode(): EpisodeEntity {
    return EpisodeEntity(
        air_date, characters, created, episode, id, name, url
    )
}