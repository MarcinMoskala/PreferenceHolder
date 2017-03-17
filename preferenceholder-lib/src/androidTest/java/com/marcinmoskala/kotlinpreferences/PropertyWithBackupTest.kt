package com.marcinmoskala.kotlinpreferences

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PropertyWithBackupTest: BaseTest() {

    @Test
    fun booleanDefaultChangeTest() {
        testValues(PropertiesPreferences::canEatPie, true, false, true, false)
    }

    @Test
    fun intDefaultChangeTest() {
        testValues(PropertiesPreferences::pieBaked, 5, 10, 0, 10, -19)
    }

    @Test
    fun longDefaultChangeTest() {
        testValues(PropertiesPreferences::allPieInTheWorld, -1L, 10L, 0L, 100L)
    }

    @Test
    fun floatDefaultChangeTest() {
        testValues(PropertiesPreferences::pieEaten, 0.0F, 10F, 0.06F, 100F)
    }

    @Test
    fun stringDefaultChangeTest() {
        testValues(PropertiesPreferences::bestPieName, "Pie", "BlueberryPie", "SuperPie")
    }
}
