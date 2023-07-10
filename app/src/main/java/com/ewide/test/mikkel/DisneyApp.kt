package com.ewide.test.mikkel

import android.app.Application
import com.ewide.test.mikkel.di.ApiModule
import com.ewide.test.mikkel.di.RepositoryModule
import com.ewide.test.mikkel.di.UseCaseModule
import com.ewide.test.mikkel.di.ViewModelModule
import com.ewide.test.mikkel.di.appmodule.MainAppModule
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