package com.marcinmoskala.kotlinpreferences.bindings

import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import java.lang.reflect.Type
import kotlin.concurrent.thread
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

internal class PropertyWithBackup<T : Any>(clazz: KClass<T>, default: T, type: Type, key: String?) : ReadWriteProperty<PreferenceHolder, T> {

    val preferenceBinder = PreferenceFieldBinder<T>(clazz, default, type, key)

    var field: T? = null

    override operator fun getValue(thisRef: PreferenceHolder, property: KProperty<*>): T =
            field ?: preferenceBinder.getValue(thisRef, property).apply { field = this }

    override fun setValue(thisRef: PreferenceHolder, property: KProperty<*>, value: T) {
        if(value == field) return
        field = value
        thread {
            preferenceBinder.setValue(thisRef, property, value)
        }
    }
}