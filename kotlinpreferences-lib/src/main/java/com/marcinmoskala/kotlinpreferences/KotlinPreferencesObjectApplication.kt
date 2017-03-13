package com.marcinmoskala.kotlinpreferences

import android.app.Application

class KotlinPreferencesObjectApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        PreferenceHolder.setContext(applicationContext)
    }
}