package com.marcinmoskala.kotlinpreferences

import android.content.Context
import android.content.SharedPreferences
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class NullableClassesChangeTest {

    val preferences: SharedPreferences
        get() = InstrumentationRegistry.getTargetContext().preferences

    init {
        preferences.clear()
    }

    @Test
    fun characterTest() {
        assertNull(preferences.character)
        preferences.character = Character("Marcin", "Human", "Wizzard")
        assertEquals(Character("Marcin", "Human", "Wizzard"), preferences.character!!)
        preferences.character = preferences.character!!.copy(race = "SuperHuman")
        assertEquals("SuperHuman", preferences.character!!.race)
        assertEquals(Character("Marcin", "SuperHuman", "Wizzard"), preferences.character!!)
    }

    @Test
    fun bigObjectTest() {
        assertNull(preferences.savedGame)
        preferences.savedGame = Game(Character("Marcin", "Human", "Wizzard"), GameMode.Hard, 100)
        assertEquals(Character("Marcin", "Human", "Wizzard"), preferences.savedGame!!.character)
        assertEquals(GameMode.Hard, preferences.savedGame!!.gameMode)
        assertEquals(100, preferences.savedGame!!.level)
    }
}
