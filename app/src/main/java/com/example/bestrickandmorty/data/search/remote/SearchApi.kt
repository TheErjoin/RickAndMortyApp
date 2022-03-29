package com.example.bestrickandmorty.data.search.remote

import com.example.bestrickandmorty.data.character.dto.CharacterModelDto
import com.example.bestrickandmorty.data.common.response.RickAndMortyResponse
import com.example.bestrickandmorty.data.episode.dto.EpisodeModelDto
import com.example.bestrickandmorty.data.location.dto.LocationModelDto
import retrofit2.http.GET
import retrofit2.http.Query


interface SearchApi {

    @GET("api/location/")
    suspend fun getLocation(
        @Query("name") name: String
    ): RickAndMortyResponse<LocationModelDto>

    @GET("/api/episode")
    suspend fun getEpisode(
        @Query("name") name: String
    ): RickAndMortyResponse<EpisodeModelDto>

    @GET("/api/character")
    suspend fun getCharacter(
        @Query("name") name: String
    ): RickAndMortyResponse<CharacterModelDto>
}