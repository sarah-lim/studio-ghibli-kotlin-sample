package com.slim.studiog.ui

import android.app.Application
import com.slim.studiog.BuildConfig
import timber.log.Timber

class StudioGhibliApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}