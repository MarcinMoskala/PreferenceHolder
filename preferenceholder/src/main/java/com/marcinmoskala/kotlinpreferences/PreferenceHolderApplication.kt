package com.marcinmoskala.kotlinpreferences

import android.app.Application

class PreferenceHolderApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        PreferenceHolder.setContext(applicationContext)
    }
}