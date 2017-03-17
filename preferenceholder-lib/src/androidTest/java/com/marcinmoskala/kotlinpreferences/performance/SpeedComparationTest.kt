package com.marcinmoskala.kotlinpreferences.performance

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.marcinmoskala.kotlinpreferences.BaseTest
import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.reflect.KMutableProperty0

@RunWith(AndroidJUnit4::class)
class SpeedComparationTest: BaseTest() {

    fun measureTime(f: () -> Unit): Long {
        val startTime = System.currentTimeMillis()
        f()
        val endTime = System.currentTimeMillis()
        return endTime - startTime
    }

    @Test
    fun propertyReadTest() {
        val normalReadTime = getReadTime(ComparePreferences::f)
        val propertyReadTime = getReadTime(ComparePreferences::p)
        Assert.assertTrue(normalReadTime > propertyReadTime)
    }

    @Test
    fun propertyWriteTest() {
        val bigList = ListPackage((1..10000).toList())
        val normalWriteTime = measureTime {
            ComparePreferences.fl = bigList
        }
        val propertyWriteTime = measureTime {
            ComparePreferences.pl = bigList
        }
        Assert.assertTrue(normalWriteTime > propertyWriteTime)
    }

    private fun getReadTime(property: KMutableProperty0<Int>): Long {
        property.set(10)
        return measureTime {
            repeat(200) { property.get() }
        }
    }
}
