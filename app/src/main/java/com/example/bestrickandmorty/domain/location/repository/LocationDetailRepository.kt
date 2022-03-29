package com.example.bestrickandmorty.domain.location.repository

import com.example.bestrickandmorty.domain.common.base.BaseResult
import com.example.bestrickandmorty.domain.location.model.LocationEntity
import kotlinx.coroutines.flow.Flow

interface LocationDetailRepository {

    fun getLocationDetail(id: Int): Flow<BaseResult<LocationEntity, String>>

}