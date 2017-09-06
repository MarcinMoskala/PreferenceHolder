package com.marcinmoskala.kotlinpreferences

import android.support.test.runner.AndroidJUnit4
import com.marcinmoskala.kotlinpreferences.TestPreferences.className
import com.marcinmoskala.kotlinpreferences.TestPreferences.experience
import com.marcinmoskala.kotlinpreferences.TestPreferences.isMonsterKiller
import com.marcinmoskala.kotlinpreferences.TestPreferences.monstersKilled
import com.marcinmoskala.kotlinpreferences.TestPreferences.numberOfHahaInLough
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CleaningTest: BaseTest() {

    @Test
    fun afterCleaningPropertiesAreSetToDefaultTest() {
        TestPreferences.canEatPie = false
        TestPreferences.pieBaked = 1000
        TestPreferences.allPieInTheWorld = 9090
        TestPreferences.pieEaten = 190F
        TestPreferences.bestPieName = "Marcin"
        TestPreferences.clear()
        assertEquals(true, TestPreferences.canEatPie)
        assertEquals(5, TestPreferences.pieBaked)
        assertEquals(-1L, TestPreferences.allPieInTheWorld)
        assertEquals(0.0F, TestPreferences.pieEaten)
        assertEquals("Pie", TestPreferences.bestPieName)
    }

    @Test
    fun afterCleaningNullablePropertiesAreSetToNullTest() {
        TestPreferences.isMonsterKiller = false
        TestPreferences.monstersKilled = 1000
        TestPreferences.numberOfHahaInLough = 9090
        TestPreferences.experience = 190F
        TestPreferences.className = "Marcin"
        TestPreferences.clear()
        assertEquals(null, isMonsterKiller)
        assertEquals(null, monstersKilled)
        assertEquals(null, numberOfHahaInLough)
        assertEquals(null, experience)
        assertEquals(null, className)
    }

    @Test
    fun cleaningIsOnlyApplyedToSinglePrefClass() {
        TestPreferences.isMonsterKiller = false
        TestPreferences.monstersKilled = 1000
        assertEquals(false, isMonsterKiller)
        assertEquals(1000, monstersKilled)
    }
}
