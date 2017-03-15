package com.marcinmoskala.kotlinpreferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.marcinmoskala.kotlinpreferences.bindings.PreferenceFieldBinder
import com.marcinmoskala.kotlinpreferences.bindings.PreferenceFieldBinderNullable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass

abstract class PreferenceHolder {

    protected inline fun <reified T : Any> bindToPreferenceField(default: T?, key: String? = null): ReadWriteProperty<PreferenceHolder, T>
            = bindToPreferenceField(T::class, default, key)

    protected inline fun <reified T : Any> bindToPreferenceFieldNullable(key: String? = null): ReadWriteProperty<PreferenceHolder, T?>
            = bindToPreferenceFieldNullable(T::class, key)

    protected fun <T: Any> bindToPreferenceField(clazz: KClass<T>, default: T?, key: String?): ReadWriteProperty<PreferenceHolder, T>
            = PreferenceFieldBinder(clazz, default, key)

    protected fun <T: Any> bindToPreferenceFieldNullable(clazz: KClass<T>, key: String?): ReadWriteProperty<PreferenceHolder, T?>
            = PreferenceFieldBinderNullable(clazz, key)

    companion object {

        internal lateinit var preferences: SharedPreferences

        var preferencesGson: Gson = GsonBuilder().create()

        fun setContext(context: Context) {
            preferences = PreferenceManager.getDefaultSharedPreferences(context)
        }

        fun clear() = preferences.edit().clear().apply()
    }
}