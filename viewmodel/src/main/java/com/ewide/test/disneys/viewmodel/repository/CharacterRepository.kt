package com.ewide.test.disneys.viewmodel.repository

import com.ewide.test.disneys.model.ListCharacterResponse
import com.ewide.test.disneys.model.OneCharacterResponse
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getAllCharacter(page: Int): Flow<ListCharacterResponse?>
    suspend fun getOneCharacter(id: Int): Flow<OneCharacterResponse?>
    suspend fun searchCharacter(name: String): Flow<OneCharacterResponse?>
}