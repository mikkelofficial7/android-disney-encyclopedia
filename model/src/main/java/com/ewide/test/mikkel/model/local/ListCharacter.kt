package com.ewide.test.mikkel.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_item_character")
data class ListCharacter(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var gameID: String? = null,
    var steamAppID: String? = null,
    var cheapest: String? = null,
    var cheapestDealID: String? = null,
    var external: String? = null,
    var internalName: String? = null,
    var thumb: String? = null,
    var isFavorite: Boolean? = false
)