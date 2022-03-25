package com.example.bestrickandmorty.data.location

import com.example.bestrickandmorty.data.location.pagingsource.LocationPagingSource
import com.example.bestrickandmorty.data.location.remote.LocationApi
import com.example.bestrickandmorty.domain.common.base.BaseRepository
import com.example.bestrickandmorty.domain.location.repository.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(private val api: LocationApi) : BaseRepository(),
    LocationRepository {

    override fun getLocationList() = doPaging(LocationPagingSource(api))
}