@file:Suppress("UNCHECKED_CAST")
package com.marcinmoskala.kotlinpreferences.bindings

import android.content.SharedPreferences
import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

open class PreferenceFieldBinder<T : Any>(val clazz: KClass<T>, val default: T?, val key: String? = null) : ReadWriteProperty<PreferenceHolder, T> {

    override operator fun getValue(thisRef: PreferenceHolder, property: KProperty<*>): T = readValue(property)

    override fun setValue(thisRef: PreferenceHolder, property: KProperty<*>, value: T) {
        PreferenceHolder.preferences.edit().apply { putValue(property, value) }.apply()
    }

    private fun readValue(property: KProperty<*>): T = PreferenceHolder.preferences.getValue(property)

    private fun SharedPreferences.getValue(property: KProperty<*>): T {
        return when (clazz.simpleName) {
            "Long" -> getLong(getKey(property), default as Long) as T
            "Int" -> getInt(getKey(property), default as Int) as T
            "String" -> getString(getKey(property), default as? String) as T
            "Boolean" -> getBoolean(getKey(property), default as Boolean) as T
            "Float" -> getFloat(getKey(property), default as Float) as T
            else -> getString(getKey(property), default?.toJson()).fromJson(clazz)
        }
    }

    private fun SharedPreferences.Editor.putValue(property: KProperty<*>, value: T) {
        when (clazz.simpleName) {
            "Long" -> putLong(getKey(property), value as Long)
            "Int" -> putInt(getKey(property), value as Int)
            "String" -> putString(getKey(property), value as String?)
            "Boolean" -> putBoolean(getKey(property), value as Boolean)
            "Float" -> putFloat(getKey(property), value as Float)
            else -> putString(getKey(property), value.toJson())
        }
    }

    private fun getKey(property: KProperty<*>) = key ?: "${property.name}Key"
}