package com.example.bestrickandmorty.data.character.dto

import com.example.bestrickandmorty.domain.character.model.CharacterLocationEntity

class LocationModelDto(

    val name: String,
    val url: String

)

fun LocationModelDto.toLocation(): CharacterLocationEntity {
    return CharacterLocationEntity(name, url)
}