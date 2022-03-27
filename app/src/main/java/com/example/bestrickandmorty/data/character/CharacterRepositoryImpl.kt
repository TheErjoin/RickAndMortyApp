package com.example.bestrickandmorty.data.character

import com.example.bestrickandmorty.data.character.dto.toCharacter
import com.example.bestrickandmorty.data.character.pagingsource.CharacterPagingSource
import com.example.bestrickandmorty.data.character.remote.CharacterApi
import com.example.bestrickandmorty.domain.character.repository.CharacterDetailRepository
import com.example.bestrickandmorty.domain.character.repository.CharacterRepository
import com.example.bestrickandmorty.domain.common.base.BaseRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(private val characterApi: CharacterApi) :
    BaseRepository(), CharacterRepository, CharacterDetailRepository {

    override fun getCharacterList(
        name: String?,
        status: String?,
        gender: String?
    ) = doPaging(
        CharacterPagingSource(characterApi, name, status, gender)
    )

    override fun getDetailCharacter(id: Int) = doExecution {
        characterApi.getCharacterDetail(id).toCharacter()
    }

}