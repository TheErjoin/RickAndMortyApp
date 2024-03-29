package com.example.bestrickandmorty.data.episode.pagingsource

import com.example.bestrickandmorty.data.episode.dto.EpisodeModelDto
import com.example.bestrickandmorty.data.episode.dto.toEpisode
import com.example.bestrickandmorty.data.episode.remote.EpisodeApi
import com.example.bestrickandmorty.domain.common.base.BasePagingSource
import com.example.bestrickandmorty.domain.episode.model.EpisodeEntity

class EpisodePagingSource(private val api: EpisodeApi, val name: String?, val episode: String?) :
    BasePagingSource<EpisodeModelDto, EpisodeEntity>({
        api.getEpisode(it, name, episode)
    }, { mapData -> mapData.map { it.toEpisode() } }) {
}