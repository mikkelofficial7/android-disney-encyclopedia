package com.ewide.test.mikkel.model

data class ListCharacterResponse(
    var info : InfoResponse? = InfoResponse(),
    var data : ArrayList<DataResponse> = arrayListOf()
)