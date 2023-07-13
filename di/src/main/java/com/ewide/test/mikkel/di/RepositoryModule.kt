package com.ewide.test.mikkel.di

import com.ewide.test.mikkel.viewmodel.repository.CharacterRepository
import com.ewide.test.mikkel.viewmodel.repository.CharacterRepositoryImpl
import org.koin.dsl.module

class RepositoryModule {
    companion object {
        val repositoryModule = module(override = true) {
            single<CharacterRepository> { return@single CharacterRepositoryImpl(get(), get()) }
        }
    }
}