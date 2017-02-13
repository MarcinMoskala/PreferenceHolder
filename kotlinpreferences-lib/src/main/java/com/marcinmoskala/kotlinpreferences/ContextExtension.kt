package com.marcinmoskala.kotlinpreferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

val Context.preferences: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(this)

fun SharedPreferences.clear() = edit().clear().apply()