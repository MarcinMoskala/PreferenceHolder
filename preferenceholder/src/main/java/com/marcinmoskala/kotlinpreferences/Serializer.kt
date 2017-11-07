package com.marcinmoskala.kotlinpreferences

interface Serializer {

    fun serialize(toSerialize: Any?): String?

    fun <T> deserialize(serialized: String?): Any?
}
