package com.ewide.test.mikkel.di

import com.ewide.test.mikkel.viewmodel.CharacterDetailVM
import com.ewide.test.mikkel.viewmodel.CharacterFavoriteVM
import com.ewide.test.mikkel.viewmodel.CharacterListVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ViewModelModule {
    companion object {
        val viewModelModule = module(override = true) {
            viewModel { CharacterListVM(get(), get()) }
            viewModel { CharacterDetailVM(get(), get()) }
            viewModel { CharacterFavoriteVM(get()) }
        }
    }
}