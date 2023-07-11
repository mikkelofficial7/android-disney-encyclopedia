package com.ewide.test.mikkel.viewmodel.usecase

import com.ewide.test.mikkel.model.ListCharacterResponse
import com.ewide.test.mikkel.model.OneCharacterResponse
import com.ewide.test.mikkel.viewmodel.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class CharacterUseCase(
    private val repository: CharacterRepository
) {
    suspend fun getAllCharacter(title: String) : Flow<List<ListCharacterResponse?>?> {
        return repository.getAllCharacter(title)
    }

    suspend fun getOneCharacter(id: Int) : Flow<OneCharacterResponse?> {
        return repository.getOneCharacter(id)
    }
}