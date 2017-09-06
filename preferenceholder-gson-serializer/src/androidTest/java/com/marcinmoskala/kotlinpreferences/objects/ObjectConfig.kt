package com.marcinmoskala.kotlinpreferences.objects

import com.marcinmoskala.kotlinpreferences.PreferenceHolder

object ComplexTestPreferences: PreferenceHolder() {
    var character: Character? by bindToPreferenceFieldNullable()
    var savedGame: Game? by bindToPreferenceFieldNullable()
}

data class Character(
        val name: String,
        val race: String,
        val clazz: String
)

data class Game(
        val character: Character,
        val gameMode: GameMode,
        val level: Int
)

enum class GameMode {
    Easy, Normal, Hard
}