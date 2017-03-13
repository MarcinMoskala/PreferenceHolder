@file:Suppress("UNCHECKED_CAST")
package com.marcinmoskala.kotlinpreferences.bindings

import android.content.SharedPreferences
import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

internal open class PreferenceFieldBinder<T : Any>(val clazz: KClass<T>, val default: T?, val key: String? = null) : ReadWriteProperty<PreferenceHolder, T> {

    override operator fun getValue(thisRef: PreferenceHolder, property: KProperty<*>): T = readValue(property, PreferenceHolder.preferences)

    override fun setValue(thisRef: PreferenceHolder, property: KProperty<*>, value: T) {
        PreferenceHolder.preferences.edit().apply {
            when (clazz.simpleName) {
                "Long" -> putLong(getKey(property), value as Long)
                "Int" -> putInt(getKey(property), value as Int)
                "String" -> putString(getKey(property), value as String?)
                "Boolean" -> putBoolean(getKey(property), value as Boolean)
                "Float" -> putFloat(getKey(property), value as Float)
                else -> putString(getKey(property), value.toJson())
            }
        }.apply()
    }

    private fun readValue(property: KProperty<*>, thisRef: SharedPreferences): T = when (clazz.simpleName) {
        "Long" -> thisRef.getLong(getKey(property), default as Long) as T
        "Int" -> thisRef.getInt(getKey(property), default as Int) as T
        "String" -> thisRef.getString(getKey(property), default as? String) as T
        "Boolean" -> thisRef.getBoolean(getKey(property), default as Boolean) as T
        "Float" -> thisRef.getFloat(getKey(property), default as Float) as T
        else -> thisRef.getString(getKey(property), default?.toJson()).fromJson(clazz)
    }

    private fun getKey(property: KProperty<*>) = key ?: "${property.name}Key"
}