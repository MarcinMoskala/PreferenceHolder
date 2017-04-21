package com.marcinmoskala.kotlinpreferences

import android.support.test.runner.AndroidJUnit4
import com.marcinmoskala.kotlinpreferences.TestPreferences.className
import com.marcinmoskala.kotlinpreferences.TestPreferences.experience
import com.marcinmoskala.kotlinpreferences.TestPreferences.isMonsterKiller
import com.marcinmoskala.kotlinpreferences.TestPreferences.monstersKilled
import com.marcinmoskala.kotlinpreferences.TestPreferences.numberOfHahaInLough
import org.junit.Assert
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
        Assert.assertEquals(true, TestPreferences.canEatPie)
        Assert.assertEquals(5, TestPreferences.pieBaked)
        Assert.assertEquals(-1L, TestPreferences.allPieInTheWorld)
        Assert.assertEquals(0.0F, TestPreferences.pieEaten)
        Assert.assertEquals("Pie", TestPreferences.bestPieName)
    }
}
