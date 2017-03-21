package com.marcinmoskala.kotlinpreferences.purejunit

import android.support.test.runner.AndroidJUnit4
import com.marcinmoskala.kotlinpreferences.BaseTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SimpleTest: BaseTest() {

    @Test
    fun simpleOverrideTest() {
        overridePrefs()
        val pref = OverridablePrefenences.get()
        Assert.assertTrue(pref.canEatPie)
        Assert.assertTrue(pref.pieBaked == 10)
        pref.pieBaked = 20
        Assert.assertTrue(pref.pieBaked == 20)
        Assert.assertTrue(pref.allPieInTheWorld == 15L)
        Assert.assertTrue(pref.bestPieName == "Mine")
        pref.bestPieName = "No, mine"
        Assert.assertTrue(pref.bestPieName == "No, mine")
    }

    @Test
    fun objectUsageTest() {
        overridePrefs()
        val c = SomeDomainClass()
        Assert.assertTrue(c.pieICanEat() == 15L)
    }

    private fun overridePrefs() {
        OverridablePrefenences.override = object : IOverridablePrefenences {
            override var canEatPie: Boolean = true
            override var pieBaked: Int = 10
            override var allPieInTheWorld: Long = 15
            override var pieEaten: Float = 11F
            override var bestPieName: String = "Mine"
        }
    }

}
