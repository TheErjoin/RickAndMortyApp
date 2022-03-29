package com.example.bestrickandmorty.data.location

import com.example.bestrickandmorty.data.location.dto.toLocation
import com.example.bestrickandmorty.data.location.pagingsource.LocationPagingSource
import com.example.bestrickandmorty.data.location.remote.LocationApi
import com.example.bestrickandmorty.domain.common.base.BaseRepository
import com.example.bestrickandmorty.domain.location.repository.LocationDetailRepository
import com.example.bestrickandmorty.domain.location.repository.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(private val api: LocationApi) : BaseRepository(),
    LocationRepository, LocationDetailRepository {

    override fun getLocationList(name: String?) = doPaging(LocationPagingSource(api, name))

    override fun getLocationDetail(id: Int) = doExecution {
        api.getLocationDetail(id).toLocation()
    }
}