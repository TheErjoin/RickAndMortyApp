package com.example.bestrickandmorty.data.episode.remote

import com.example.bestrickandmorty.data.common.response.RickAndMortyResponse
import com.example.bestrickandmorty.data.episode.dto.EpisodeModelDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeApi {

    @GET("/api/episode")
    suspend fun getEpisode(
        @Query("page") page: Int,
        @Query("name") name: String?,
        @Query("episode") episode: String?
    ): RickAndMortyResponse<EpisodeModelDto>

    @GET("/api/episode/{id}")
    suspend fun getEpisodeDetail(
        @Path("id") id: Int
    ): EpisodeModelDto
}