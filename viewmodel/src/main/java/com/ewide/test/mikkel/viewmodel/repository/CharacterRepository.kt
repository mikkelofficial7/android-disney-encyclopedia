package com.ewide.test.mikkel.viewmodel.repository

import com.ewide.test.mikkel.model.ListCharacterResponse
import com.ewide.test.mikkel.model.OneCharacterResponse
import com.ewide.test.mikkel.model.local.ListCharacter
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getAllCharacter(title: String): Flow<List<ListCharacterResponse?>?>
    suspend fun getOneCharacter(id: String): Flow<OneCharacterResponse?>
    suspend fun getOneCharacterFromLocalDb(isAscending: Boolean): List<ListCharacter?>?
    suspend fun addCharacterToFavorite(item: ListCharacter)
    suspend fun removeCharacterFromFavorite(item: ListCharacter)

}