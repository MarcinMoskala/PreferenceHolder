package com.marcinmoskala.kotlinpreferences

import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PreferenceFieldTest: BaseTest() {

    @Test
    fun booleanDefaultChangeTest() {
        testValues(TestPreferences::canEatPie, true, false, true, false)
    }

    @Test
    fun intDefaultChangeTest() {
        testValues(TestPreferences::pieBaked, 5, 10, 0, 10, -19)
    }

    @Test
    fun longDefaultChangeTest() {
        testValues(TestPreferences::allPieInTheWorld, -1L, 10L, 0L, 100L)
    }

    @Test
    fun floatDefaultChangeTest() {
        testValues(TestPreferences::pieEaten, 0.0F, 10F, 0.06F, 100F)
    }

    @Test
    fun stringDefaultChangeTest() {
        testValues(TestPreferences::bestPieName, "Pie", "BlueberryPie", "SuperPie")
    }
}
