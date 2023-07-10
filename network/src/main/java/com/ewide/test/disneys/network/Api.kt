package com.ewide.test.disneys.network

import com.ewide.test.disneys.model.ListCharacterResponse
import com.ewide.test.disneys.model.OneCharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("character")
    suspend fun getAllCharacter(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 10,
    ): ListCharacterResponse

    @GET("character/{id}")
    suspend fun getOneCharacter(
        @Path("id") id: Int,
    ): OneCharacterResponse

    @GET("character")
    suspend fun searchCharacter(
        @Query("name") name: String,
    ): OneCharacterResponse
}