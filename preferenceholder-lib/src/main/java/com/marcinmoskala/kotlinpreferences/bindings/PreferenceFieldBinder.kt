@file:Suppress("UNCHECKED_CAST")

package com.marcinmoskala.kotlinpreferences.bindings

import android.content.SharedPreferences
import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import java.lang.reflect.Type
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

internal open class PreferenceFieldBinder<T : Any>(
        val clazz: KClass<T>,
        val default: T,
        val type: Type,
        val key: String?
) : ReadWriteProperty<PreferenceHolder, T> {


    override fun setValue(thisRef: PreferenceHolder, property: KProperty<*>, value: T) {
        if (PreferenceHolder.setOverride != null) {
            PreferenceHolder.setOverride!!.invoke(thisRef, property.name, value)
        } else {
            PreferenceHolder.preferences.edit().apply { putValue(clazz, value, getKey(property)) }.apply()
        }
    }

    override operator fun getValue(thisRef: PreferenceHolder, property: KProperty<*>): T {
        return if (PreferenceHolder.getOverride != null) {
            PreferenceHolder.getOverride!!.invoke(thisRef, property.name) as T
        } else {
            PreferenceHolder.preferences.run { readValue(property) }
        }
    }

    private fun SharedPreferences.readValue(property: KProperty<*>): T = when (clazz.simpleName) {
        "Long" -> getLong(getKey(property), default as Long) as T
        "Int" -> getInt(getKey(property), default as Int) as T
        "String" -> getString(getKey(property), default as? String) as T
        "Boolean" -> getBoolean(getKey(property), default as Boolean) as T
        "Float" -> getFloat(getKey(property), default as Float) as T
        else -> getString(getKey(property), default.toJson()).fromJson(type)
    }

    private fun getKey(property: KProperty<*>) = key ?: "${property.name}Key"
}