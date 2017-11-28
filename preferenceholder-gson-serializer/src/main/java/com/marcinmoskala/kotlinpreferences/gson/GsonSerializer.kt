package com.marcinmoskala.kotlinpreferences.gson

import com.google.gson.Gson
import com.marcinmoskala.kotlinpreferences.Serializer
import java.lang.reflect.Type

class GsonSerializer(val gson: Gson) : Serializer {

    override fun serialize(toSerialize: Any?): String? = gson.toJson(toSerialize)

    override fun deserialize(serialized: String?, type: Type): Any? = try {
        gson.fromJson<Any>(serialized, type)
    } catch (e: Throwable) {
        throw Error("Error in parsing to $type. The string to parse: \"$this\"", e)
    }
}