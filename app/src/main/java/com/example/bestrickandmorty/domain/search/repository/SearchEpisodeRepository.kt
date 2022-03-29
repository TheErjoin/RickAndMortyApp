package com.example.bestrickandmorty.domain.search.repository

import com.example.bestrickandmorty.domain.common.base.BaseResult
import com.example.bestrickandmorty.domain.episode.model.EpisodeEntity
import kotlinx.coroutines.flow.Flow

interface SearchEpisodeRepository {

    fun getSearchingEpisode(name: String): Flow<BaseResult<List<EpisodeEntity>, String>>

}