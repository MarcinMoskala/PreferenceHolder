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
    fun nullAfterCleanupTest() {
        TestPreferences.monstersKilled = 10
        PreferenceHolder.clear()
        Assert.assertNull(TestPreferences.monstersKilled)
    }

    @Test
    fun defaultAfterCleanupTest() {
        TestPreferences.pieBaked = 10
        PreferenceHolder.clear()
        Assert.assertEquals(5, TestPreferences.pieBaked)
    }

    @Test
    fun multipleCleanupTest() {
        TestPreferences.monstersKilled = 10
        TestPreferences.pieBaked = 10
        PreferenceHolder.clear()
        Assert.assertNull(TestPreferences.monstersKilled)
        Assert.assertEquals(5, TestPreferences.pieBaked)
    }
}
