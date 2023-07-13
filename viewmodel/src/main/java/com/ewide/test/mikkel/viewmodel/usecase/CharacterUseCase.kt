package com.ewide.test.mikkel.viewmodel.usecase

import com.ewide.test.mikkel.model.ListCharacterResponse
import com.ewide.test.mikkel.model.OneCharacterResponse
import com.ewide.test.mikkel.model.local.ListCharacter
import com.ewide.test.mikkel.viewmodel.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class CharacterUseCase(
    private val repository: CharacterRepository
) {
    suspend fun getAllCharacter(title: String) : Flow<List<ListCharacterResponse?>?> {
        return repository.getAllCharacter(title)
    }

    suspend fun getOneCharacter(id: String) : Flow<OneCharacterResponse?> {
        return repository.getOneCharacter(id)
    }

    suspend fun getOneCharacterFromLocalDb(isAscending: Boolean) :List<ListCharacter?>? {
        return repository.getOneCharacterFromLocalDb(isAscending)
    }

    suspend fun addCharacterToFavorite(item: ListCharacter) {
        repository.addCharacterToFavorite(item)
    }

    suspend fun removeCharacterFromFavorite(item: ListCharacter) {
        return repository.removeCharacterFromFavorite(item)
    }
}