package com.marcinmoskala.kotlinpreferences

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.marcinmoskala.kotlinpreferences.ComplexTestPreferences.character
import com.marcinmoskala.kotlinpreferences.ComplexTestPreferences.savedGame
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ObjectsTest {

    init {
        PreferenceHolder.setContext(InstrumentationRegistry.getTargetContext())
        PreferenceHolder.clear()
    }

    @Test
    fun characterTest() {
        val character1 = Character("Marcin", "Human", "Wizzard")
        val character2 = Character("Marcin", "SuperHuman", "Wizzard")
        testValues(ComplexTestPreferences::character, null, character1, character2)
    }

    @Test
    fun bigObjectTest() {
        val game1 = Game(Character("Marcin", "Human", "Wizzard"), GameMode.Hard, 100)
        testValues(ComplexTestPreferences::savedGame, null, game1)
    }
}
