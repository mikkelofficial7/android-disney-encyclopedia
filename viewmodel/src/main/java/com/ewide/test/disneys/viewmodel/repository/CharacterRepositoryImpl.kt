package com.ewide.test.disneys.viewmodel.repository

import com.ewide.test.disneys.extension.flowOnValue
import com.ewide.test.disneys.model.ListCharacterResponse
import com.ewide.test.disneys.model.OneCharacterResponse
import com.ewide.test.disneys.network.Api
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl(private val api: Api) : CharacterRepository {
    override suspend fun getAllCharacter(page: Int): Flow<ListCharacterResponse?> {
        return flowOnValue(api.getAllCharacter(page))
    }

    override suspend fun getOneCharacter(id: Int): Flow<OneCharacterResponse?> {
        return flowOnValue(api.getOneCharacter(id))
    }

    override suspend fun searchCharacter(name: String): Flow<OneCharacterResponse?> {
        return flowOnValue(api.searchCharacter(name))
    }
}