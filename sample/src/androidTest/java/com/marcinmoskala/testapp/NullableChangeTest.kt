package com.marcinmoskala.testapp

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import com.marcinmoskala.testapp.TestPreferences.className
import com.marcinmoskala.testapp.TestPreferences.experience
import com.marcinmoskala.testapp.TestPreferences.isMonsterKiller
import com.marcinmoskala.testapp.TestPreferences.monstersKilled
import com.marcinmoskala.testapp.TestPreferences.numberOfHahaInLough
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NullableChangeTest {

    init {
        PreferenceHolder.setContext(InstrumentationRegistry.getTargetContext())
        TestPreferences.clear()
    }

    @Test
    fun booleanDefaultChangeTest() {
        assertNull(isMonsterKiller)
        isMonsterKiller = true
        assertTrue(isMonsterKiller!!)
        isMonsterKiller = false
        assertTrue(!isMonsterKiller!!)
        isMonsterKiller = null
        assertNull(isMonsterKiller)
    }

    @Test
    fun intDefaultChangeTest() {
        assertNull(monstersKilled)
        monstersKilled = 1
        assertEquals(1, monstersKilled!!)
        monstersKilled = monstersKilled?.plus(1)
        assertEquals(2, monstersKilled!!)
        monstersKilled = null
        assertNull(monstersKilled)

    }

    @Test
    fun longDefaultChangeTest() {
        assertNull(numberOfHahaInLough)
        numberOfHahaInLough = 10
        assertEquals(10, numberOfHahaInLough!!)
        numberOfHahaInLough = 100
        assertEquals(100, numberOfHahaInLough!!)
        numberOfHahaInLough = null
        assertNull(numberOfHahaInLough)
    }

    @Test
    fun floatDefaultChangeTest() {
        assertNull(experience)
        experience = 0.0F
        assertEquals(0.0F, experience!!)
        experience = 10.0F
        assertEquals(10.0F, experience!!)
        experience = null
        assertNull(experience)

    }

    @Test
    fun stringDefaultChangeTest() {
        assertNull(className)
        className = "Rouge"
        assertEquals("Rouge", className!!)
        className = "Wizard"
        assertEquals("Wizard", className!!)
        className = null
        assertNull(className)
    }
}
