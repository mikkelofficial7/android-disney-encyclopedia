package com.ewide.test.mikkel.model

import com.ewide.test.mikkel.model.local.ListCharacter

data class ListCharacterResponse(
    var gameID: String? = null,
    var steamAppID: String? = null,
    var cheapest: String? = null,
    var cheapestDealID: String? = null,
    var external: String? = null,
    var internalName: String? = null,
    var thumb: String? = null,
    var isFavorite: Boolean = false
) {
    fun setItemToFavoriteCharacterLocal() : ListCharacter {
        return ListCharacter(
            gameID = gameID,
            steamAppID = steamAppID,
            cheapest = cheapest,
            cheapestDealID = cheapestDealID,
            external = external,
            internalName = internalName,
            thumb = thumb
        )
    }
}