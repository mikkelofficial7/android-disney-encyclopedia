package com.ewide.test.mikkel.viewmodel.repository

import com.ewide.test.mikkel.extension.flowOnValue
import com.ewide.test.mikkel.model.ListCharacterResponse
import com.ewide.test.mikkel.model.OneCharacterResponse
import com.ewide.test.mikkel.model.local.ListCharacter
import com.ewide.test.mikkel.network.Api
import com.ewide.test.mikkel.room.DBConfig
import com.ewide.test.mikkel.room.queryAllFavoriteCharacter
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl(private val api: Api, private val roomConfig: DBConfig) : CharacterRepository {
    override suspend fun getAllCharacter(title: String): Flow<List<ListCharacterResponse?>?> {
        return flowOnValue(api.getAllCharacter(title))
    }

    override suspend fun getOneCharacter(id: String): Flow<OneCharacterResponse?> {
        return flowOnValue(api.getOneCharacter(id))
    }

    override suspend fun getOneCharacterFromLocalDb(isAscending: Boolean): List<ListCharacter?>? {
        return roomConfig.dataDao().queryAllFavoriteCharacter(isAscending)
    }

    override suspend fun addCharacterToFavorite(item: ListCharacter) {
        flowOnValue(roomConfig.dataDao().insert(item))
    }

    override suspend fun removeCharacterFromFavorite(item: ListCharacter) {
        flowOnValue(roomConfig.dataDao().deleteFavoriteItemById(item.gameID.orEmpty()))
    }
}