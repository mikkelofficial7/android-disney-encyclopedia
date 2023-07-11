package com.ewide.test.mikkel.viewmodel.usecase

import com.ewide.test.mikkel.model.ListCharacterResponse
import com.ewide.test.mikkel.model.OneCharacterResponse
import com.ewide.test.mikkel.viewmodel.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class CharacterUseCase(
    private val repository: CharacterRepository
) {
    suspend fun getAllCharacter() : Flow<List<ListCharacterResponse?>?> {
        return repository.getAllCharacter()
    }

    suspend fun getOneCharacter(id: Int) : Flow<OneCharacterResponse?> {
        return repository.getOneCharacter(id)
    }

    suspend fun searchCharacter(name: String) : Flow<OneCharacterResponse?> {
        return repository.searchCharacter(name)
    }
}