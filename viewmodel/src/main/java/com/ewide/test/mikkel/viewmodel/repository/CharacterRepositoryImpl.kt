package com.ewide.test.mikkel.viewmodel.repository

import com.ewide.test.mikkel.extension.flowOnValue
import com.ewide.test.mikkel.model.ListCharacterResponse
import com.ewide.test.mikkel.model.OneCharacterResponse
import com.ewide.test.mikkel.network.Api
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl(private val api: Api) : CharacterRepository {
    override suspend fun getAllCharacter(title: String): Flow<List<ListCharacterResponse?>?> {
        return flowOnValue(api.getAllCharacter(title))
    }

    override suspend fun getOneCharacter(id: String): Flow<OneCharacterResponse?> {
        return flowOnValue(api.getOneCharacter(id))
    }
}