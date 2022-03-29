package com.example.bestrickandmorty.domain.search.entity

import com.example.bestrickandmorty.domain.character.model.CharacterEntity
import com.example.bestrickandmorty.domain.episode.model.EpisodeEntity
import com.example.bestrickandmorty.domain.location.model.LocationEntity

sealed class FilterEntity {

    class CharactersEntity(val characterEntity: CharacterEntity) : FilterEntity()
    class EpisodesEntity(val episodeEntity: EpisodeEntity) : FilterEntity()
    class LocationsEntity(val locationEntity: LocationEntity) : FilterEntity()
}

fun CharacterEntity.toCharacterEntity() = FilterEntity.CharactersEntity(
    CharacterEntity(
        id,
        name,
        status,
        species,
        type,
        gender,
        origin,
        location,
        image,
        episode,
        url,
        created
    )
)

fun LocationEntity.toLocationEntity() = FilterEntity.LocationsEntity(
    LocationEntity(created, dimension, id, name, residents, type, url)
)

fun EpisodeEntity.toEpisodeEntity() = FilterEntity.EpisodesEntity(
    EpisodeEntity(air_date, characters, created, episode, id, name, url)
)