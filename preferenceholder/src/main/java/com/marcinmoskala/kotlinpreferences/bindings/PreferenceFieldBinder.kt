package com.marcinmoskala.kotlinpreferences.bindings

import android.content.SharedPreferences
import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import com.marcinmoskala.kotlinpreferences.PreferenceHolder.Companion.getPreferencesOrThrowError
import com.marcinmoskala.kotlinpreferences.PreferenceHolder.Companion.testingMode
import java.lang.reflect.Type
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

internal class PreferenceFieldBinder<T : Any>(
        private val clazz: KClass<T>,
        private val type: Type,
        private val default: T,
        private val key: String?
) : ReadWriteProperty<PreferenceHolder, T>, Clearable {

    override fun clear(thisRef: PreferenceHolder, property: KProperty<*>) {
        setValue(thisRef, property, default)
    }

    override fun clearCache() {
    }

    var field: T? = null

    override operator fun getValue(thisRef: PreferenceHolder, property: KProperty<*>): T = when {
        testingMode -> field ?: default
        else -> readValue(property)
    }

    override fun setValue(thisRef: PreferenceHolder, property: KProperty<*>, value: T) {
        if (testingMode) {
            if (value == field) return
            field = value
        } else {
            saveNewValue(property, value)
        }
    }

    private fun saveNewValue(property: KProperty<*>, value: T) {
        val pref = getPreferencesOrThrowError()
        pref.edit().apply { putValue(clazz, value, getKey(key, property)) }.apply()
    }

    private fun readValue(property: KProperty<*>): T {
        val pref = getPreferencesOrThrowError()
        return pref.getValue(property)
    }

    private fun SharedPreferences.getValue(property: KProperty<*>): T {
        val key = getKey(key, property)
        return getFromPreference(clazz, type, default, key) as T
    }
}