@file:Suppress("UNCHECKED_CAST")

package com.marcinmoskala.kotlinpreferences.bindings

import android.content.SharedPreferences
import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

internal open class PreferenceFieldBinderNullable<T : Any>(val clazz: KClass<T>, val key: String? = null) : ReadWriteProperty<PreferenceHolder, T?> {

    val pref: SharedPreferences
        get() = PreferenceHolder.preferences

    override operator fun getValue(thisRef: PreferenceHolder, property: KProperty<*>): T? = readValue(property)

    override fun setValue(thisRef: PreferenceHolder, property: KProperty<*>, value: T?) {
        if (value == null) {
            removeValue(property)
        } else {
            saveValue(property, value)
        }
    }

    private fun readValue(property: KProperty<*>): T? {
        val key = getKey(property)
        if (!pref.contains(key)) return null
        return pref.getByKey(key)
    }

    private fun SharedPreferences.getByKey(key: String): T? = when (clazz.simpleName) {
        "Long" -> getLong(key, -1L) as? T
        "Int" -> getInt(key, -1) as? T
        "String" -> getString(key, null) as? T
        "Boolean" -> getBoolean(key, false) as? T
        "Float" -> getFloat(key, -1.0F) as T
        else -> getString(key, null)?.fromJson(clazz)
    }

    private fun removeValue(property: KProperty<*>) {
        pref.edit()
                .remove(getKey(property))
                .apply()
    }

    private fun saveValue(property: KProperty<*>, value: T?) {
        pref.edit().apply { putValue(property, value) }.apply()
    }

    private fun SharedPreferences.Editor.putValue(property: KProperty<*>, value: T?) {
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