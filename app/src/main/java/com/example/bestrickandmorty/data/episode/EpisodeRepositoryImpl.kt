package com.example.bestrickandmorty.data.episode

import com.example.bestrickandmorty.data.episode.pagingsource.EpisodePagingSource
import com.example.bestrickandmorty.data.episode.remote.EpisodeApi
import com.example.bestrickandmorty.domain.common.base.BaseRepository
import com.example.bestrickandmorty.domain.episode.repository.EpisodeRepository
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(private val api: EpisodeApi) : BaseRepository(),
    EpisodeRepository {
    override fun getEpisodeList() = doPaging(EpisodePagingSource(api))


}