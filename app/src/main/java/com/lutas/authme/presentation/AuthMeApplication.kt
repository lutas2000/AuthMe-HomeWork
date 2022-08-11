package com.lutas.authme.presentation

import android.app.Application
import com.lutas.authme.di.appModule
import com.lutas.authme.di.remoteModule
import com.lutas.authme.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AuthMeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin{
            androidLogger()
            androidContext(this@AuthMeApplication)
            modules(remoteModule, repositoryModule, appModule)
        }
    }
}