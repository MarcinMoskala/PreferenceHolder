package com.marcinmoskala.kotlinpreferences.bindings

import android.content.SharedPreferences
import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

internal open class PreferenceFieldBinderNullable<T : Any>(val clazz: KClass<T>, val key: String? = null) : ReadWriteProperty<PreferenceHolder, T?> {

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