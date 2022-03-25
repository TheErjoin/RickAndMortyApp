package com.example.bestrickandmorty.data.location.remote

import com.example.bestrickandmorty.data.common.response.RickAndMortyResponse
import com.example.bestrickandmorty.data.location.dto.LocationModelDto
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {

    @GET("api/location/")
    suspend fun getLocation(
        @Query("page") page: Int
    ): RickAndMortyResponse<LocationModelDto>

}