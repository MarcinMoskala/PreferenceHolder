package com.marcinmoskala.testapp

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import com.marcinmoskala.testapp.TestPreferences.canEatPie
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.reflect.KMutableProperty0

@RunWith(AndroidJUnit4::class)
class DefaultChangeTest {

    init {
        PreferenceHolder.setContext(InstrumentationRegistry.getTargetContext())
        TestPreferences.clear()
    }

    @Test
    fun booleanDefaultChangeTest() {
        testBoolean(TestPreferences::canEatPie)
    }

    private fun testBoolean(property: KMutableProperty0<Boolean>) {
        assertTrue(property.get())
        property.set(false)
        canEatPie = false
        assertTrue(!canEatPie)
        canEatPie = true
        assertTrue(canEatPie)
    }

    @Test
    fun intDefaultChangeTest() {
        assertEquals(0, TestPreferences.pieBaked)
        TestPreferences.pieBaked = 10
        assertEquals(10, TestPreferences.pieBaked)
        TestPreferences.pieBaked += 10
        assertEquals(20, TestPreferences.pieBaked)
    }

    @Test
    fun longDefaultChangeTest() {
        assertEquals(0L, TestPreferences.allPieInTheWorld)
        TestPreferences.allPieInTheWorld = 10
        assertEquals(10L, TestPreferences.allPieInTheWorld)
        TestPreferences.allPieInTheWorld += 10
        assertEquals(20L, TestPreferences.allPieInTheWorld)
    }

    @Test
    fun floatDefaultChangeTest() {
        assertEquals(0F, TestPreferences.pieEaten)
        for (i in 1..10) TestPreferences.pieEaten += 1F
        assertEquals(10F, TestPreferences.pieEaten)
        TestPreferences.pieEaten *= 2
        assertEquals(20F, TestPreferences.pieEaten)
    }

    @Test
    fun stringDefaultChangeTest() {
        assertEquals("Pie", TestPreferences.bestPieName)
        TestPreferences.bestPieName = "BlueberryPie"
        assertEquals("BlueberryPie", TestPreferences.bestPieName)
        TestPreferences.bestPieName = "SuperPie"
        assertEquals("SuperPie", TestPreferences.bestPieName)
    }
}
