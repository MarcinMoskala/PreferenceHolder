package com.marcinmoskala.kotlinpreferences.bindings

import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import kotlin.reflect.KClass

internal fun Any?.toJson() = if (this == null) null else PreferenceHolder.preferencesGson.toJson(this)

internal fun <T : Any> String.fromJson(clazz: KClass<T>) = try {
    PreferenceHolder.preferencesGson.fromJson(this, clazz.java)
} catch (e: Throwable) {
    throw Error("Error in parsing to ${clazz.java}. Possibly lack of gson serializers. The string to parse: \"$this\"", e)
}