package com.example.bestrickandmorty.data.character.dto

import com.example.bestrickandmorty.domain.character.model.CharacterEntity

class CharacterModelDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginModelDto,
    val location: LocationModelDto,
    val image: String,
    val episode: MutableList<String>,
    val url: String,
    val created: String,
)

fun CharacterModelDto.toCharacter(): CharacterEntity {
    return CharacterEntity(
        id,
        name,
        status,
        species,
        type,
        gender,
        origin.toOrigin(),
        location.toLocation(),
        image,
        episode,
        url,
        created
    )
}