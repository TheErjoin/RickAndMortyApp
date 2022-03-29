package com.example.bestrickandmorty.domain.episode.repository

import com.example.bestrickandmorty.domain.common.base.BaseResult
import com.example.bestrickandmorty.domain.episode.model.EpisodeEntity
import kotlinx.coroutines.flow.Flow

interface EpisodeDetailRepository {

    fun getEpisodeDetail(id: Int): Flow<BaseResult<EpisodeEntity, String>>

}