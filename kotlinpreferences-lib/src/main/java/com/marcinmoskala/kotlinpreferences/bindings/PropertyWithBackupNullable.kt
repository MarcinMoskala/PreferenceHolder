package com.marcinmoskala.kotlinpreferences.bindings

import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import kotlin.concurrent.thread
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class PropertyWithBackupNullable<T : Any>(clazz: KClass<T>, key: String? = null) : PreferenceFieldBinderNullable<T>(clazz, key) {

    var propertySet: Boolean = false
    var field: T? = null

    override operator fun getValue(thisRef: PreferenceHolder, property: KProperty<*>): T? = if (propertySet) field else {
        val newValue = super.getValue(thisRef, property)
        field = newValue
        propertySet = true
        newValue
    }

    override fun setValue(thisRef: PreferenceHolder, property: KProperty<*>, value: T?) {
        field = value
        propertySet = true
        thread {
            super.setValue(thisRef, property, value)
        }
    }
}