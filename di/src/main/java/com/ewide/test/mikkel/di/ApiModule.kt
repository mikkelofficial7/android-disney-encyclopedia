package com.ewide.test.mikkel.di

import com.ewide.test.mikkel.network.Api
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