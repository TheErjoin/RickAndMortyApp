package com.example.bestrickandmorty.domain.episode.usecases

import com.example.bestrickandmorty.domain.episode.repository.EpisodeRepository
import javax.inject.Inject

class GetEpisodeListUseCase @Inject constructor(private val repository: EpisodeRepository) {

    operator fun invoke(name: String?,episode: String?) = repository.getEpisodeList(name,episode)

}