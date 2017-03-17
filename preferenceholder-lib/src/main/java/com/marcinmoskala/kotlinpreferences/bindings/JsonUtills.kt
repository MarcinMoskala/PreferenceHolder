package com.marcinmoskala.kotlinpreferences.bindings

import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import java.lang.reflect.Type
import kotlin.reflect.KClass

internal fun Any?.toJson() = if (this == null) null else PreferenceHolder.preferencesGson.toJson(this)

internal fun <T : Any> String.fromJson(type: Type) = try {
    PreferenceHolder.preferencesGson.fromJson<T>(this, type)
} catch (e: Throwable) {
    throw Error("Error in parsing to $type. Possibly lack of gson serializers. The string to parse: \"$this\"", e)
}