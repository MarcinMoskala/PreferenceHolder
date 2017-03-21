package com.marcinmoskala.kotlinpreferences.junit

import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import org.junit.Assert
import org.junit.Test

class SimpleTest {

    @Test
    fun simpleSetTest() {
        var a: Int? = null
        PreferenceHolder.setOverride = { c, p, v ->
            when (p) {
                "i" -> a = v as Int
            }
        }
        JUnitPref1.i = 5
        println(a)
        Assert.assertTrue(a == 5)
    }

    @Test
    fun simpleMultipleSetTest() {
        var a: Int = 0
        PreferenceHolder.setOverride = { c, p, v ->
            when (c::class to p) {
                JUnitPref1::class to "i" -> a += v as Int * 2
                JUnitPref2::class to "i" -> a += v as Int
            }
        }
        JUnitPref1.i = 5
        JUnitPref2.i = 2
        println(a)
        Assert.assertTrue(a == 12)
    }


}