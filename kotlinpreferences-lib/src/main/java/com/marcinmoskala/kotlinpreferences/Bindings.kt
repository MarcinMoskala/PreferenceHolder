@file:Suppress("UNCHECKED_CAST")
package com.marcinmoskala.kotlinpreferences

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class PreferenceFieldBinder<T : Any>(val clazz: KClass<T>, val default: T?, val key: String? = null) : ReadWriteProperty<PreferenceHolder, T> {

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

class PreferenceFieldBinderNullable<T : Any>(val clazz: KClass<T>, val key: String? = null) : ReadWriteProperty<PreferenceHolder, T?> {

    override operator fun getValue(thisRef: PreferenceHolder, property: KProperty<*>): T? = readValue(property, PreferenceHolder.preferences)

    override fun setValue(thisRef: PreferenceHolder, property: KProperty<*>, value: T?) {
        if (value == null) {
            removeValue(property, PreferenceHolder.preferences)
        } else {
            saveValue(property, PreferenceHolder.preferences, value)
        }
    }

    private fun readValue(property: KProperty<*>, thisRef: SharedPreferences): T? {
        val key = getKey(property)
        if (!thisRef.contains(key)) return null
        return when (clazz.simpleName) {
            "Long" -> thisRef.getLong(key, -1L) as? T
            "Int" -> thisRef.getInt(key, -1) as? T
            "String" -> thisRef.getString(key, null) as? T
            "Boolean" -> thisRef.getBoolean(key, false) as? T
            "Float" -> thisRef.getFloat(getKey(property), -1.0F) as T
            else -> thisRef.getString(key, null)?.fromJson(clazz)
        }
    }

    private fun removeValue(property: KProperty<*>, thisRef: SharedPreferences) {
        thisRef.edit().remove(getKey(property)).apply()
    }

    private fun saveValue(property: KProperty<*>, thisRef: SharedPreferences, value: T?) {
        thisRef.edit().apply {
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

    private fun getKey(property: KProperty<*>) = key ?: "${property.name}Key"
}


private fun Any?.toJson() = if (this == null) null else PreferenceHolder.preferencesGson.toJson(this)

private fun <T : Any> String.fromJson(clazz: KClass<T>) = try {
    PreferenceHolder.preferencesGson.fromJson(this, clazz.java)
} catch (e: Throwable) {
    throw Error("Error in parsing to ${clazz.java}. Possibly lack of gson serializers. The string to parse: \"$this\"", e)
}