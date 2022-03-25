package com.example.bestrickandmorty.data.character.pagingsource

import com.example.bestrickandmorty.data.character.dto.CharacterModelDto
import com.example.bestrickandmorty.data.character.dto.toCharacter
import com.example.bestrickandmorty.data.character.remote.CharacterApi
import com.example.bestrickandmorty.domain.character.model.CharacterEntity
import com.example.bestrickandmorty.domain.common.base.BasePagingSource

class CharacterPagingSource(
    private val api: CharacterApi, private val name: String?,
    private val status: String?,
    private val type: String?,
    private val gender: String?,
) :
    BasePagingSource<CharacterModelDto, CharacterEntity>
        ({ api.getCharacterResponse(it, name, status, type, gender) },
        { mapData -> mapData.map { it.toCharacter() } })