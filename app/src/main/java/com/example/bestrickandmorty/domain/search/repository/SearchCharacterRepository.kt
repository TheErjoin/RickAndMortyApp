package com.example.bestrickandmorty.domain.search.repository

import com.example.bestrickandmorty.domain.character.model.CharacterEntity
import com.example.bestrickandmorty.domain.common.base.BaseResult
import kotlinx.coroutines.flow.Flow

interface SearchCharacterRepository {

    fun getSearchingCharacter(name: String): Flow<BaseResult<List<CharacterEntity>, String>>

}