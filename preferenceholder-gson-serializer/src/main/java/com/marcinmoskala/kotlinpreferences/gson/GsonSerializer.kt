package com.marcinmoskala.kotlinpreferences.gson

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.marcinmoskala.kotlinpreferences.Serializer

class GsonSerializer(val gson: Gson): Serializer {

    override fun serialize(toSerialize: Any?): String? = gson.toJson(toSerialize)

    override fun <T> deserialize(serialized: String?, clazz: Class<T>): Any? = try {
        gson.fromJson<T>(serialized, clazz)
    } catch (e: Throwable) {
        val type = object : TypeToken<T>() {}.type
        throw Error("Error in parsing to $type. The string to parse: \"$this\"", e)
    }
}