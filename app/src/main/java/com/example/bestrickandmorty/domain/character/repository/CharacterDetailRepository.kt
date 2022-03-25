package com.example.bestrickandmorty.domain.character.repository

import com.example.bestrickandmorty.domain.character.model.CharacterEntity
import com.example.bestrickandmorty.domain.common.base.BaseResult
import kotlinx.coroutines.flow.Flow


interface CharacterDetailRepository {

    fun getDetailCharacter(id: Int): Flow<BaseResult<CharacterEntity, String>>

}