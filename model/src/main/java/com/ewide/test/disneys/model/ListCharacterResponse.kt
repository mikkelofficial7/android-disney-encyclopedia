package com.ewide.test.disneys.model

import com.google.gson.annotations.SerializedName

data class ListCharacterResponse(
    @SerializedName("info")
    var info : Info? = Info(),
    @SerializedName("data")
    var data : ArrayList<Data> = arrayListOf()
)

data class Info (
    @SerializedName("totalPages")
    var totalPages : Int? = null,
    @SerializedName("count")
    var count : Int? = null,
    @SerializedName("previousPage")
    var previousPage : String? = null,
    @SerializedName("nextPage")
    var nextPage : String? = null
)

data class Data (
    @SerializedName("_id")
    var id : Int? = null,
    @SerializedName("sourceUrl")
    var sourceUrl : String? = null,
    @SerializedName("name")
    var name : String? = null,
    @SerializedName("imageUrl")
    var imageUrl : String? = null,
)