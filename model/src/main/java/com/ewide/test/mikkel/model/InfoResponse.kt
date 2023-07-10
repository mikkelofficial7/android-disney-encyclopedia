package com.ewide.test.mikkel.model

data class InfoResponse (
    var totalPages : Int? = null,
    var count : Long? = null,
    var previousPage : String? = null,
    var nextPage : String? = null
)