package com.marcinmoskala.kotlinpreferences

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import kotlin.reflect.KClass

var globalGson: Gson = GsonBuilder().create()

internal fun Any?.toJson() = if (this == null) null else globalGson.toJson(this)

@JvmName("toJsonNonNullable")
internal fun Any.toJson() = globalGson.toJson(this)!!

internal inline fun <reified T : Any> String.fromJson() = fromJson(T::class)

internal fun <T : Any> String.fromJson(clazz: KClass<T>) = try {
    globalGson.fromJson(this, clazz.java)
} catch (e: JsonSyntaxException) {
    throw Error("Error in parsing \"$this\" to ${clazz.java}", e)
}