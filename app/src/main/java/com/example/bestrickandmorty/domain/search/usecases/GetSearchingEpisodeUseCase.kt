package com.example.bestrickandmorty.domain.search.usecases

import com.example.bestrickandmorty.domain.search.repository.SearchEpisodeRepository
import javax.inject.Inject

class GetSearchingEpisodeUseCase @Inject constructor(private val repository: SearchEpisodeRepository) {

    operator fun invoke(name: String) = repository.getSearchingEpisode(name)

}