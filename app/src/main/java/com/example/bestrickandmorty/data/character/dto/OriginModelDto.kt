package com.example.bestrickandmorty.data.character.dto

import com.example.bestrickandmorty.domain.character.model.OriginEntity

class OriginModelDto(

    val name: String,
    val url: String

)

fun OriginModelDto.toOrigin(): OriginEntity {
    return OriginEntity(name, url)
}