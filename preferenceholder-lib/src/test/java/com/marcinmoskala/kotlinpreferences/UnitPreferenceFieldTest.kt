package com.marcinmoskala.kotlinpreferences

import org.junit.Test
import org.junit.runner.RunWith
import com.marcinmoskala.kotlinpreferences.junit.testValues
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UnitPreferenceFieldTest : JunitBaseTest() {

    @Test
    fun booleanDefaultChangeTest() {
        testValues(UnitTestPreferences::canEatPie, true, false, true, false)
    }

    @Test
    fun intDefaultChangeTest() {
        testValues(UnitTestPreferences::pieBaked, 5, 10, 0, 10, -19)
    }

    @Test
    fun longDefaultChangeTest() {
        testValues(UnitTestPreferences::allPieInTheWorld, -1L, 10L, 0L, 100L)
    }

    @Test
    fun floatDefaultChangeTest() {
        testValues(UnitTestPreferences::pieEaten, 0.0F, 10F, 0.06F, 100F)
    }

    @Test
    fun stringDefaultChangeTest() {
        testValues(UnitTestPreferences::bestPieName, "Pie", "BlueberryPie", "SuperPie")
    }
}
