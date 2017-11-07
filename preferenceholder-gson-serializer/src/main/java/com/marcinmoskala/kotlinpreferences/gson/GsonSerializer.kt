package com.marcinmoskala.kotlinpreferences.gson

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.marcinmoskala.kotlinpreferences.Serializer

class GsonSerializer(val gson: Gson): Serializer {

    override fun serialize(toSerialize: Any?): String? = gson.toJson(toSerialize)

    override fun <T> deserialize(serialized: String?): Any? {
        val type = object : TypeToken<T>() {}.type
        return try {
            gson.fromJson<T>(serialized, type)
        } catch (e: Throwable) {
            throw Error("Error in parsing to $type. The string to parse: \"$this\"", e)
        }
    }
}