package com.example.bestrickandmorty.data.episode

import com.example.bestrickandmorty.data.episode.dto.toEpisode
import com.example.bestrickandmorty.data.episode.pagingsource.EpisodePagingSource
import com.example.bestrickandmorty.data.episode.remote.EpisodeApi
import com.example.bestrickandmorty.domain.common.base.BaseRepository
import com.example.bestrickandmorty.domain.episode.repository.EpisodeDetailRepository
import com.example.bestrickandmorty.domain.episode.repository.EpisodeRepository
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(private val api: EpisodeApi) : BaseRepository(),
    EpisodeRepository, EpisodeDetailRepository {

    override fun getEpisodeList(name: String?, episode: String?) =
        doPaging(EpisodePagingSource(api, name, episode))

    override fun getEpisodeDetail(id: Int) = doExecution {
        api.getEpisodeDetail(id).toEpisode()
    }
}