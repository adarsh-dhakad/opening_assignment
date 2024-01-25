package com.opening.app

import android.app.Application
import com.opening.openingassignment.di.appModule
import com.opening.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(networkModule,appModule))
        }
    }
}