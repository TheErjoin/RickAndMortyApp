package com.example.bestrickandmorty.data.location.pagingsource

import com.example.bestrickandmorty.data.location.dto.LocationModelDto
import com.example.bestrickandmorty.data.location.dto.toLocation
import com.example.bestrickandmorty.data.location.remote.LocationApi
import com.example.bestrickandmorty.domain.common.base.BasePagingSource
import com.example.bestrickandmorty.domain.location.model.LocationEntity

class LocationPagingSource(private val api: LocationApi) :
    BasePagingSource<LocationModelDto, LocationEntity>
        ({ api.getLocation(it) },
        { data -> data.map { it.toLocation() } }) {
}