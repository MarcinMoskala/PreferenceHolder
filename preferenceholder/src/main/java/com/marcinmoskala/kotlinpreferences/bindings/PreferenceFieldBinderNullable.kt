package com.marcinmoskala.kotlinpreferences.bindings

import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import com.marcinmoskala.kotlinpreferences.PreferenceHolder.Companion.getPreferencesOrThrowError
import com.marcinmoskala.kotlinpreferences.PreferenceHolder.Companion.testingMode
import java.lang.reflect.Type
import kotlin.concurrent.thread
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

internal class PreferenceFieldBinderNullable<T : Any>(
        private val clazz: KClass<T>,
        private val type: Type,
        private val key: String?
) : ReadWriteProperty<PreferenceHolder, T?>, Clearable {

    override fun clear(thisRef: PreferenceHolder, property: KProperty<*>) {
        setValue(thisRef, property, null)
    }

    override fun clearCache() {
        propertySet = false
        field = null
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

        if (!testingMode)
            saveNewValue(property, value)
    }

    private fun saveNewValue(property: KProperty<*>, value: T?) {
        thread {
            if (value == null) {
                removeValue(property)
            } else {
                saveValue(property, value)
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
        pref.edit()
                .remove(getKey(key, property))
                .apply()
    }

    private fun saveValue(property: KProperty<*>, value: T) {
        val pref = getPreferencesOrThrowError()
        pref.edit().apply { putValue(clazz, value, getKey(key, property)) }.apply()
    }
}