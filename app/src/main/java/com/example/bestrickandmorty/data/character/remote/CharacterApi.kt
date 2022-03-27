package com.example.bestrickandmorty.data.character.remote

import com.example.bestrickandmorty.data.character.dto.CharacterModelDto
import com.example.bestrickandmorty.data.common.response.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

    @GET("/api/character")
    suspend fun getCharacterResponse(
        @Query("page") page: Int,
        @Query("name") name: String?,
        @Query("status") status: String?,
        @Query("gender") gender: String?
    ): RickAndMortyResponse<CharacterModelDto>

    @GET("/api/character/{id}")
    suspend fun getCharacterDetail(
        @Path("id") id: Int
    ): CharacterModelDto


}