package com.marcinmoskala.kotlinpreferences

import android.content.SharedPreferences
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.marcinmoskala.kotlinpreferences.TestPreferences.className
import com.marcinmoskala.kotlinpreferences.TestPreferences.experience
import com.marcinmoskala.kotlinpreferences.TestPreferences.isMonsterKiller
import com.marcinmoskala.kotlinpreferences.TestPreferences.monstersKilled
import com.marcinmoskala.kotlinpreferences.TestPreferences.numberOfHahaInLough
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SpeedComparationTest {

    init {
        PreferenceHolder.setContext(InstrumentationRegistry.getTargetContext())
        PreferenceHolder.clear()
    }

    fun measureTime(f: () -> Unit): Long {
        val startTime = System.currentTimeMillis()
        f()
        val endTime = System.currentTimeMillis()
        return endTime - startTime
    }

    @Test
    fun propertyReadTest() {
        @Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE") var a = 0
        ComparePreferences.f = 10
        val normalReadTime = measureTime {
            repeat(200) {
                a = ComparePreferences.f
            }
        }
        ComparePreferences.p = 10
        val propertyReadTime = measureTime {
            repeat(200) {
                a = ComparePreferences.p
            }
        }
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
}
