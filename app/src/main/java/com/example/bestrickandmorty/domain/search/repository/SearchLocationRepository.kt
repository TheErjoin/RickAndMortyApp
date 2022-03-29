package com.example.bestrickandmorty.domain.search.repository

import com.example.bestrickandmorty.domain.common.base.BaseResult
import com.example.bestrickandmorty.domain.location.model.LocationEntity
import kotlinx.coroutines.flow.Flow

interface SearchLocationRepository {

    fun getSearchingLocation(name: String): Flow<BaseResult<List<LocationEntity>, String>>

}