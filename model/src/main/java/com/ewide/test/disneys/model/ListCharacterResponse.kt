package com.ewide.test.disneys.model

data class ListCharacterResponse(
    var info : InfoResponse? = InfoResponse(),
    var data : ArrayList<DataResponse> = arrayListOf()
)