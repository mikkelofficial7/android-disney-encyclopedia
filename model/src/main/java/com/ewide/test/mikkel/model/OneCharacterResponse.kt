package com.ewide.test.mikkel.model

data class OneCharacterResponse (
    val info: Info,
    val cheapestPriceEver: CheapestPriceEver,
    val deals: List<Deal>
)

data class CheapestPriceEver (
    val price: String,
    val date: Long
)

data class Deal (
    val storeID: String,
    val dealID: String,
    val price: String,
    val retailPrice: String,
    val savings: String
)

data class Info (
    val title: String,
    val steamAppID: Any? = null,
    val thumb: String
)