package com.example.bestrickandmorty.domain.character.usecases

import com.example.bestrickandmorty.domain.character.repository.CharacterDetailRepository
import javax.inject.Inject

class GetCharacterDetailDataUseCase @Inject constructor(private val repository: CharacterDetailRepository) {

    operator fun invoke(id: Int) = repository.getDetailCharacter(id)

}