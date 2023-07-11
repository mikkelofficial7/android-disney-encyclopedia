package com.ewide.test.mikkel.network

import com.ewide.test.mikkel.model.ListCharacterResponse
import com.ewide.test.mikkel.model.OneCharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("games")
    suspend fun getAllCharacter(
        @Query("title") title: String = "batman"
    ): List<ListCharacterResponse>

    @GET("character/{id}")
    suspend fun getOneCharacter(
        @Path("id") id: Int,
    ): OneCharacterResponse

    @GET("character")
    suspend fun searchCharacter(
        @Query("name") name: String,
    ): OneCharacterResponse
}