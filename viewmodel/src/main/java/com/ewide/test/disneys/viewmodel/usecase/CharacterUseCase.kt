package com.ewide.test.disneys.viewmodel.usecase

import com.ewide.test.disneys.model.ListCharacterResponse
import com.ewide.test.disneys.model.OneCharacterResponse
import com.ewide.test.disneys.viewmodel.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class CharacterUseCase(
    private val repository: CharacterRepository
) {
    suspend fun getAllCharacter(page: Int) : Flow<ListCharacterResponse?> {
        return repository.getAllCharacter(page)
    }

    suspend fun getOneCharacter(id: Int) : Flow<OneCharacterResponse?> {
        return repository.getOneCharacter(id)
    }

    suspend fun searchCharacter(name: String) : Flow<OneCharacterResponse?> {
        return repository.searchCharacter(name)
    }
}