package com.ewide.test.mikkel.viewmodel.repository

import com.ewide.test.mikkel.extension.flowOnValue
import com.ewide.test.mikkel.model.ListCharacterResponse
import com.ewide.test.mikkel.model.OneCharacterResponse
import com.ewide.test.mikkel.network.Api
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl(private val api: Api) : CharacterRepository {
    override suspend fun getAllCharacter(page: Int?): Flow<List<ListCharacterResponse?>?> {
        return flowOnValue(api.getAllCharacter(page))
    }

    override suspend fun getOneCharacter(id: Int): Flow<OneCharacterResponse?> {
        return flowOnValue(api.getOneCharacter(id))
    }

    override suspend fun searchCharacter(name: String): Flow<OneCharacterResponse?> {
        return flowOnValue(api.searchCharacter(name))
    }
}