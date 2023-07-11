package com.ewide.test.mikkel.viewmodel.repository

import com.ewide.test.mikkel.model.ListCharacterResponse
import com.ewide.test.mikkel.model.OneCharacterResponse
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getAllCharacter(title: String): Flow<List<ListCharacterResponse?>?>
    suspend fun getOneCharacter(id: String): Flow<OneCharacterResponse?>
}