package com.ewide.test.disneys.di

import com.ewide.test.disneys.viewmodel.usecase.CharacterUseCase
import org.koin.dsl.module

class UseCaseModule {
    companion object {
        val useCaseModule = module(override = true) {
            single { CharacterUseCase(get()) }
        }
    }
}