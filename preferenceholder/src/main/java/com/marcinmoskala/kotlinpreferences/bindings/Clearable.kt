package com.marcinmoskala.kotlinpreferences.bindings

import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import kotlin.reflect.KProperty

interface Clearable {
    fun clear(thisRef: PreferenceHolder, property: KProperty<*>)
    fun clearCache()
}