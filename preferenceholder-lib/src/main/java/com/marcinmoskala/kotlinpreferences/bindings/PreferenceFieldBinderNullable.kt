package com.marcinmoskala.kotlinpreferences.bindings

import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import com.marcinmoskala.kotlinpreferences.PreferenceHolder.Companion.getPreferencesOrThrowError
import com.marcinmoskala.kotlinpreferences.PreferenceHolder.Companion.testingMode
import java.lang.reflect.Type
import kotlin.concurrent.thread
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

internal class PreferenceFieldBinderNullable<T : Any>(val clazz: KClass<T>, val type: Type, val key: String?) : ReadWriteProperty<PreferenceHolder, T?>, PreferenceBinding {

    override fun clear() {
        field = null
        propertySet = false
    }

    var propertySet: Boolean = false
    var field: T? = null

    override operator fun getValue(thisRef: PreferenceHolder, property: KProperty<*>): T? = when {
        testingMode || propertySet -> field
        else -> readAndSetValue(property)
    }

    private fun readAndSetValue(property: KProperty<*>): T? {
        val newValue = readValue(property)
        field = newValue
        propertySet = true
        return newValue
    }

    override fun setValue(thisRef: PreferenceHolder, property: KProperty<*>, value: T?) {
        propertySet = true
        if (value == field) return
        field = value
        if (!testingMode) saveValueInThread(property, value)
    }

    private fun saveValueInThread(property: KProperty<*>, value: T?) {
        thread {
            if (value == null) {
                removeValue(property)
            } else {
                saveValue(property, clazz, key, value)
            }
        }
    }

    private fun readValue(property: KProperty<*>): T? {
        val key = getKey(key, property)
        val pref = getPreferencesOrThrowError()
        if (!pref.contains(key)) return null
        return pref.getFromPreference(clazz, type, key)
    }

    private fun removeValue(property: KProperty<*>) {
        val pref = getPreferencesOrThrowError()
        val key = getKey(key, property)
        pref.edit().remove(key).apply()
    }
}