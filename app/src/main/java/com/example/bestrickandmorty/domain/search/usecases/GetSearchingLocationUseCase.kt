package com.example.bestrickandmorty.domain.search.usecases

import com.example.bestrickandmorty.domain.search.repository.SearchLocationRepository
import javax.inject.Inject

class GetSearchingLocationUseCase @Inject constructor(private val repository: SearchLocationRepository) {

    operator fun invoke(name: String) = repository.getSearchingLocation(name)

}