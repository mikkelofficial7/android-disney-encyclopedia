package com.ewide.test.disneys.di

import com.ewide.test.disneys.viewmodel.repository.CharacterRepository
import com.ewide.test.disneys.viewmodel.repository.CharacterRepositoryImpl
import org.koin.dsl.module

class RepositoryModule {
    companion object {
        val repositoryModule = module(override = true) {
            single<CharacterRepository> { return@single CharacterRepositoryImpl(get()) }
        }
    }
}