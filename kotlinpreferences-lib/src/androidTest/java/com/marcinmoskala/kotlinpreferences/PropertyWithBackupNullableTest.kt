package com.marcinmoskala.kotlinpreferences

import android.content.SharedPreferences
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.marcinmoskala.kotlinpreferences.ComplexTestPreferences.character
import com.marcinmoskala.kotlinpreferences.ComplexTestPreferences.savedGame
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PropertyWithBackupNullableTest {

    init {
        PreferenceHolder.setContext(InstrumentationRegistry.getTargetContext())
        PreferenceHolder.clear()
    }

    @Test
    fun booleanDefaultChangeTest() {
        testValues(PropertiesPreferences::isMonsterKiller, null, true, false, true, null)
    }

    @Test
    fun intDefaultChangeTest() {
        testValues(PropertiesPreferences::monstersKilled, null, 10, 0, 10, -19)
    }

    @Test
    fun longDefaultChangeTest() {
        testValues(PropertiesPreferences::numberOfHahaInLough, null, 10L, 0L, 100L)
    }

    @Test
    fun floatDefaultChangeTest() {
        testValues(PropertiesPreferences::experience, null, 10F, 0F, 100F)
    }

    @Test
    fun stringDefaultChangeTest() {
        testValues(PropertiesPreferences::className, null, "BlueberryPie", "SuperPie")
    }
}
