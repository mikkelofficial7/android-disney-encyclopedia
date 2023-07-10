package com.ewide.test.mikkel.di

import com.ewide.test.mikkel.viewmodel.usecase.CharacterUseCase
import org.koin.dsl.module

class UseCaseModule {
    companion object {
        val useCaseModule = module(override = true) {
            single { CharacterUseCase(get()) }
        }
    }
}