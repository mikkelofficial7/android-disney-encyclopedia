package com.ewide.test.mikkel.network

import com.ewide.test.mikkel.model.ListCharacterResponse
import com.ewide.test.mikkel.model.OneCharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("games")
    suspend fun getAllCharacter(
        @Query("title") title: String
    ): List<ListCharacterResponse>

    @GET("games")
    suspend fun getOneCharacter(
        @Query("id") id: String,
    ): OneCharacterResponse
}