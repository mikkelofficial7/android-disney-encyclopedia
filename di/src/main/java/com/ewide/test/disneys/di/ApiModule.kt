package com.ewide.test.disneys.di

import com.example.moviedbapp.network.Api
import org.koin.dsl.module
import retrofit2.Retrofit

class ApiModule {
    companion object {
        val apiModule = module(override = true) {
            single { provideDisneyApi(get()) }
        }

        fun provideDisneyApi(retrofit: Retrofit): Api {
            return retrofit.create(Api::class.java)
        }
    }
}