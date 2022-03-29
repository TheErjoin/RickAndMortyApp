package com.example.bestrickandmorty.domain.search.usecases

import com.example.bestrickandmorty.domain.search.repository.SearchCharacterRepository
import javax.inject.Inject

class GetSearchingCharacterUseCase @Inject constructor(private val repository: SearchCharacterRepository){

    operator fun invoke(name: String) = repository.getSearchingCharacter(name)


}