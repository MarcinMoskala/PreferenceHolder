package com.marcinmoskala.kotlinpreferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.marcinmoskala.kotlinpreferences.bindings.PreferenceFieldBinder
import com.marcinmoskala.kotlinpreferences.bindings.PreferenceFieldBinderNullable
import com.marcinmoskala.kotlinpreferences.bindings.PropertyWithBackup
import com.marcinmoskala.kotlinpreferences.bindings.PropertyWithBackupNullable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

abstract class PreferenceHolder {

    protected inline fun <reified T : Any> bindToPreferenceField(default: T?, key: String? = null) = PreferenceFieldBinder(T::class, default, key)

    protected inline fun <reified T : Any> bindToPreferenceFieldNullable(key: String? = null) = PreferenceFieldBinderNullable(T::class, key)

    protected inline fun <reified T : Any> propertyWithBackup(default: T?, key: String? = null) = PropertyWithBackup(T::class, default, key)

    protected inline fun <reified T : Any> propertyWithBackupNullable(key: String? = null) = PropertyWithBackupNullable(T::class, key)

    companion object {

        internal lateinit var preferences: SharedPreferences

        var preferencesGson: Gson = GsonBuilder().create()

        fun setContext(context: Context) {
            preferences = PreferenceManager.getDefaultSharedPreferences(context)
        }

        fun clear() = preferences.edit().clear().apply()
    }
}