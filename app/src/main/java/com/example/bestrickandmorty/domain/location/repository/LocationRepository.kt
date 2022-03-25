package com.example.bestrickandmorty.domain.location.repository

import androidx.paging.PagingData
import com.example.bestrickandmorty.domain.location.model.LocationEntity
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    fun getLocationList(): Flow<PagingData<LocationEntity>>

}