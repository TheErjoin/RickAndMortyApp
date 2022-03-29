package com.example.bestrickandmorty.domain.episode.usecases

import com.example.bestrickandmorty.domain.episode.repository.EpisodeDetailRepository
import javax.inject.Inject

class GetEpisodeDetailUseCase @Inject constructor(private val repository: EpisodeDetailRepository) {

    operator fun invoke(id: Int) = repository.getEpisodeDetail(id)

}