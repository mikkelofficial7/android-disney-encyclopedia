package com.ewide.test.mikkel.model

import com.google.gson.annotations.SerializedName

data class DataResponse (
    @SerializedName("_id")
    val id: Long? = 0,
    val films: List<String>? = listOf(),
    val shortFilms: List<String>? = listOf(),
    val tvShows: List<String>? = listOf(),
    val videoGames: List<String>? = listOf(),
    val parkAttractions: List<String>? = listOf(),
    val allies: List<Any?>? = listOf(),
    val enemies: List<Any?>? = listOf(),
    val name: String? = null,
    val imageUrl: String? = null,
    val url: String? = null
)