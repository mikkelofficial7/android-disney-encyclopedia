package com.ewide.test.mikkel.di

import com.ewide.test.mikkel.viewmodel.GamesDetailVM
import com.ewide.test.mikkel.viewmodel.GamesFavoriteVM
import com.ewide.test.mikkel.viewmodel.GamesListVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ViewModelModule {
    companion object {
        val viewModelModule = module(override = true) {
            viewModel { GamesListVM(get(), get(), get()) }
            viewModel { GamesDetailVM(get(), get()) }
            viewModel { GamesFavoriteVM(get(), get(), get()) }
        }
    }
}