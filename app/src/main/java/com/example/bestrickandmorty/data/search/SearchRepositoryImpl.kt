package com.example.bestrickandmorty.data.search

import com.example.bestrickandmorty.data.character.dto.toCharacter
import com.example.bestrickandmorty.data.episode.dto.toEpisode
import com.example.bestrickandmorty.data.location.dto.toLocation
import com.example.bestrickandmorty.data.search.remote.SearchApi
import com.example.bestrickandmorty.domain.common.base.BaseRepository
import com.example.bestrickandmorty.domain.search.repository.SearchCharacterRepository
import com.example.bestrickandmorty.domain.search.repository.SearchEpisodeRepository
import com.example.bestrickandmorty.domain.search.repository.SearchLocationRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val api: SearchApi) : BaseRepository(),
    SearchCharacterRepository, SearchEpisodeRepository, SearchLocationRepository {

    override fun getSearchingCharacter(name: String) = doExecution {
        api.getCharacter(name).results.map {
            it.toCharacter()
        }
    }

    override fun getSearchingEpisode(name: String) = doExecution {
        api.getEpisode(name).results.map {
            it.toEpisode()
        }
    }

    override fun getSearchingLocation(name: String) = doExecution {
        api.getLocation(name).results.map {
            it.toLocation()
        }
    }
}