package com.ewide.test.mikkel.viewmodel.usecase

import com.ewide.test.mikkel.model.ListCharacterResponse
import com.ewide.test.mikkel.model.OneCharacterResponse
import com.ewide.test.mikkel.model.local.ListCharacter
import com.ewide.test.mikkel.viewmodel.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class CharacterUseCaseImpl(
    private val repository: CharacterRepository
) : CharacterUseCase {
    override suspend fun getAllCharacter(title: String) : Flow<List<ListCharacterResponse?>?> {
        return repository.getAllCharacter(title)
    }

    override suspend fun getOneCharacter(id: String) : Flow<OneCharacterResponse?> {
        return repository.getOneCharacter(id)
    }

    override suspend fun getOneCharacterFromLocalDb(isAscending: Boolean) :List<ListCharacter?>? {
        return repository.getOneCharacterFromLocalDb(isAscending)
    }

    override suspend fun addCharacterToFavorite(item: ListCharacter) {
        repository.addCharacterToFavorite(item)
    }

    override suspend fun removeCharacterFromFavorite(item: ListCharacter) {
        return repository.removeCharacterFromFavorite(item)
    }
}