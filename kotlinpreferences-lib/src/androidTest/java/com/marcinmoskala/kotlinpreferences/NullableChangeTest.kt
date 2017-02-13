package com.marcinmoskala.kotlinpreferences

import android.content.Context
import android.content.SharedPreferences
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class NullableChangeTest {

    val preferences: SharedPreferences
        get() = InstrumentationRegistry.getTargetContext().preferences

    init {
        preferences.clear()
    }

    @Test
    fun booleanDefaultChangeTest() {
        assertNull(preferences.isMonsterKiller)
        preferences.isMonsterKiller = true
        assert(preferences.isMonsterKiller!!)
        preferences.isMonsterKiller = false
        assert(!preferences.isMonsterKiller!!)
        preferences.isMonsterKiller = null
        assertNull(preferences.isMonsterKiller)

    }

    @Test
    fun intDefaultChangeTest() {
        assertNull(preferences.monstersKilled)
        preferences.monstersKilled = 1
        assertEquals(1, preferences.monstersKilled!!)
        preferences.monstersKilled = preferences.monstersKilled?.plus(1)
        assertEquals(2, preferences.monstersKilled!!)
        preferences.monstersKilled = null
        assertNull(preferences.monstersKilled)

    }

    @Test
    fun longDefaultChangeTest() {
        assertNull(preferences.numberOfHahaInLough)
        preferences.numberOfHahaInLough = 10
        assertEquals(10, preferences.numberOfHahaInLough!!)
        preferences.numberOfHahaInLough = 100
        assertEquals(100, preferences.numberOfHahaInLough!!)
        preferences.numberOfHahaInLough = null
        assertNull(preferences.numberOfHahaInLough)

    }

    @Test
    fun floatDefaultChangeTest() {
        assertNull(preferences.experience)
        preferences.experience = 0.0F
        assertEquals(0.0F, preferences.experience!!)
        preferences.experience = 10.0F
        assertEquals(10.0F, preferences.experience!!)
        preferences.experience = null
        assertNull(preferences.experience)

    }

    @Test
    fun stringDefaultChangeTest() {
        assertNull(preferences.className)
        preferences.className = "Rouge"
        assertEquals("Rouge", preferences.className!!)
        preferences.className = "Wizard"
        assertEquals("Wizard", preferences.className!!)
        preferences.className = null
        assertNull(preferences.className)
    }
}
