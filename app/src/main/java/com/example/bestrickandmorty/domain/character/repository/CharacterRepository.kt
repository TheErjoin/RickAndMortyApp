package com.example.bestrickandmorty.domain.character.repository

import androidx.paging.PagingData
import com.example.bestrickandmorty.domain.character.model.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacterList(
        name: String?,
        status: String?,
        gender: String?
    ): Flow<PagingData<CharacterEntity>>

}