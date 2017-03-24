package com.marcinmoskala.kotlinpreferences

import org.junit.Test
import org.junit.runner.RunWith
import com.marcinmoskala.kotlinpreferences.junit.testValues
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UnitPreferenceFieldNullableTest: JunitBaseTest() {

    @Test
    fun booleanDefaultChangeTest() {
        testValues(UnitTestPreferences::isMonsterKiller, null, true, false, true, null)
    }

    @Test
    fun intDefaultChangeTest() {
        testValues(UnitTestPreferences::monstersKilled, null, 10, 0, 10, -19)
    }

    @Test
    fun longDefaultChangeTest() {
        testValues(UnitTestPreferences::numberOfHahaInLough, null, 10L, 0L, 100L)
    }

    @Test
    fun floatDefaultChangeTest() {
        testValues(UnitTestPreferences::experience, null, 10F, 0F, 100F)
    }

    @Test
    fun stringDefaultChangeTest() {
        testValues(UnitTestPreferences::className, null, "BlueberryPie", "SuperPie")
    }
}
