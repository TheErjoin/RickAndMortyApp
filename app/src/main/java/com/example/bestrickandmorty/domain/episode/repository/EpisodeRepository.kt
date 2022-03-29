package com.example.bestrickandmorty.domain.episode.repository

import androidx.paging.PagingData
import com.example.bestrickandmorty.domain.episode.model.EpisodeEntity
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {

    fun getEpisodeList(name: String?,episode: String?): Flow<PagingData<EpisodeEntity>>

}