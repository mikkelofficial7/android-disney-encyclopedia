package com.ewide.test.disneys

import android.app.Application
import com.ewide.test.disneys.di.ApiModule
import com.ewide.test.disneys.di.RepositoryModule
import com.ewide.test.disneys.di.UseCaseModule
import com.ewide.test.disneys.di.ViewModelModule
import com.ewide.test.disneys.di.appmodule.MainAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DisneyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@DisneyApp)
            modules(MainAppModule, ApiModule.apiModule, RepositoryModule.repositoryModule,
                UseCaseModule.useCaseModule, ViewModelModule.viewModelModule
            )
        }
    }

}