package com.marcinmoskala.kotlinpreferences

import android.content.Context
import android.content.SharedPreferences
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class DefaultChangeTest {

    val preferences: SharedPreferences
        get() = InstrumentationRegistry.getTargetContext().preferences

    init {
        preferences.clear()
    }

    @Test
    fun booleanDefaultChangeTest() {
        assert(preferences.canEatPie)
        preferences.canEatPie = false
        assert(!preferences.canEatPie)
        preferences.canEatPie = true
        assert(preferences.canEatPie)
    }

    @Test
    fun intDefaultChangeTest() {
        assertEquals(0, preferences.pieBaked)
        preferences.pieBaked = 10
        assertEquals(10, preferences.pieBaked)
        preferences.pieBaked += 10
        assertEquals(20, preferences.pieBaked)
    }

    @Test
    fun longDefaultChangeTest() {
        assertEquals(0L, preferences.allPieInTheWorld)
        preferences.allPieInTheWorld = 10
        assertEquals(10L, preferences.allPieInTheWorld)
        preferences.allPieInTheWorld += 10
        assertEquals(20L, preferences.allPieInTheWorld)
    }

    @Test
    fun floatDefaultChangeTest() {
        assertEquals(0F, preferences.pieEaten)
        for (i in 1..10) preferences.pieEaten += 1F
        assertEquals(10F, preferences.pieEaten)
        preferences.pieEaten *= 2
        assertEquals(20F, preferences.pieEaten)
    }

    @Test
    fun stringDefaultChangeTest() {
        assertEquals("Pie", preferences.bestPieName)
        preferences.bestPieName = "BlueberryPie"
        assertEquals("BlueberryPie", preferences.bestPieName)
        preferences.bestPieName = "SuperPie"
        assertEquals("SuperPie", preferences.bestPieName)
    }
}
