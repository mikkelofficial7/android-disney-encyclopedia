package com.ewide.test.mikkel.di.appmodule

import com.ewide.test.mikkel.di.NetworkModule
import com.ewide.test.mikkel.di.RoomModule
import com.ewide.test.mikkel.di.SharedPrefModule
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val MainAppModule = module(override = true) {
    single { NetworkModule.provideOkHttpClient() }
    single { NetworkModule.provideRetrofitService(get()) }
    single { NetworkModule.provideNetworkHandler(androidContext()) }
    single { RoomModule.provideRoom(androidContext()) }
    single { RoomModule.provideRoom(androidContext()) }
    single { SharedPrefModule.providePreference(androidContext()) }
}