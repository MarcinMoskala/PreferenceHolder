package com.marcinmoskala.kotlinpreferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

abstract class PreferenceHolder {

    inline fun <reified T : Any> bindToPreferenceField(default: T?, key: String? = null) = PreferenceFieldBinder(T::class, default, key)

    inline fun <reified T : Any> bindToPreferenceFieldNullable(key: String? = null) = PreferenceFieldBinderNullable(T::class, key)

    companion object {

        internal lateinit var preferences: SharedPreferences

        var preferencesGson: Gson = GsonBuilder().create()

        fun setContext(context: Context) {
            preferences = PreferenceManager.getDefaultSharedPreferences(context)
        }

        fun clear() = preferences.edit().clear().apply()
    }
}