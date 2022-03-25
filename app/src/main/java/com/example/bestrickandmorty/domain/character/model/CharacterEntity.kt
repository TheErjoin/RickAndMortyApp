package com.example.bestrickandmorty.domain.character.model

import com.example.bestrickandmorty.domain.common.base.BaseId

data class CharacterEntity(
    override val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginEntity,
    val location: CharacterLocationEntity,
    val image: String,
    val episode: MutableList<String>,
    val url: String,
    val created: String,

    var firstSeenIn: String = ""
) : BaseId