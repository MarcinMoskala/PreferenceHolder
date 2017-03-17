package com.marcinmoskala.kotlinpreferences.bindings

import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import java.lang.reflect.Type
import kotlin.concurrent.thread
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

internal class PropertyWithBackupNullable<T : Any>(clazz: KClass<T>, type: Type, key: String? = null) : PreferenceFieldBinderNullable<T>(clazz, type, key) {

    var propertySet: Boolean = false
    var field: T? = null

    override operator fun getValue(thisRef: PreferenceHolder, property: KProperty<*>): T? = if (propertySet) field else {
        val newValue = super.getValue(thisRef, property)
        field = newValue
        propertySet = true
        newValue
    }

    override fun setValue(thisRef: PreferenceHolder, property: KProperty<*>, value: T?) {
        propertySet = true
        if(value == field) return
        field = value
        thread {
            super.setValue(thisRef, property, value)
        }
    }
}