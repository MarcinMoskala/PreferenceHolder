package com.marcinmoskala.kotlinpreferences.objects

import android.support.test.runner.AndroidJUnit4
import com.marcinmoskala.kotlinpreferences.GsonBaseTest
import com.marcinmoskala.kotlinpreferences.testValues
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ObjectsTest: GsonBaseTest() {

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