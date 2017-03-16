package com.marcinmoskala.kotlinpreferences

import org.junit.Assert
import kotlin.reflect.KMutableProperty0

fun <T> testValues(property: KMutableProperty0<T>, start: T?, vararg values: T) {
    Assert.assertEquals(start, property.get())
    values.forEach {
        property.set(it)
        Assert.assertEquals(it, property.get())
    }
}