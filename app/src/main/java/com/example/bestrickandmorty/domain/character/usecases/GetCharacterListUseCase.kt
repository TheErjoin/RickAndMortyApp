package com.example.bestrickandmorty.domain.character.usecases

import com.example.bestrickandmorty.domain.character.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterListUseCase
@Inject constructor(private val characterRepository: CharacterRepository) {

    operator fun invoke(
        name: String? = null,
        status: String? = null,
        gender: String? = null
    ) = characterRepository.getCharacterList(name, status, gender)

}