@file:Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")

package com.marcinmoskala.kotlinpreferences.bindings

import android.content.SharedPreferences
import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import java.lang.reflect.Type
import kotlin.reflect.KClass

internal fun SharedPreferences.Editor.putValue(clazz: KClass<*>, value: Any, key: String) {
    when (clazz.simpleName) {
        "Long" -> putLong(key, value as Long)
        "Int" -> putInt(key, value as Int)
        "String" -> putString(key, value as String?)
        "Boolean" -> putBoolean(key, value as Boolean)
        "Float" -> putFloat(key, value as Float)
        else -> putString(key, value.serialize())
    }
}

internal fun <T : Any> SharedPreferences.getFromPreference(clazz: KClass<T>, type: Type, default: T?, key: String): T? = when (clazz.simpleName) {
    "Long" -> getLong(key, default as Long) as? T
    "Int" -> getInt(key, default as Int) as? T
    "String" -> getString(key, default as? String) as? T
    "Boolean" -> getBoolean(key, default as Boolean) as? T
    "Float" -> getFloat(key, default as Float) as? T
    else -> getString(key, default.serialize()).deserialize(type)
}

internal fun <T: Any> SharedPreferences.getFromPreference(clazz: KClass<T>, type: Type, key: String): T?
        = getFromPreference(clazz, type, getDefault(clazz), key)

private fun <T: Any> String.deserialize(type: Type): T? = getSerializer().deserialize(this, type) as? T

private fun <T> T.serialize() = getSerializer().serialize(this)

private fun getSerializer() = PreferenceHolder.serializer ?: throw Error(Errors.serializerNotSet)

private fun <T: Any> getDefault(clazz: KClass<T>): T? = when(clazz.simpleName) {
    "Long" -> -1L
    "Int" -> -1
    "Boolean" -> false
    "Float" -> -1.0F
    else -> null
} as? T