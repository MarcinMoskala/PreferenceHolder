package com.marcinmoskala.kotlinpreferences

object TestPreferences: PreferenceHolder() {
    var canEatPie: Boolean by bindToPreferenceField(true)
    var pieBaked: Int by bindToPreferenceField(0)
    var allPieInTheWorld: Long by bindToPreferenceField(0)
    var pieEaten: Float by bindToPreferenceField(0.0F)
    var bestPieName: String by bindToPreferenceField("Pie")

    var isMonsterKiller: Boolean? by bindToPreferenceFieldNullable()
    var monstersKilled: Int? by bindToPreferenceFieldNullable()
    var numberOfHahaInLough: Long? by bindToPreferenceFieldNullable()
    var experience: Float? by bindToPreferenceFieldNullable()
    var className: String? by bindToPreferenceFieldNullable()
}

object ComplexTestPreferences: PreferenceHolder() {
    var character: Character? by bindToPreferenceFieldNullable()
    var savedGame: Game? by bindToPreferenceFieldNullable()
}


data class Character(
        val name: String,
        val race: String,
        val clazz: String
)

class Game(
        val character: Character,
        val gameMode: GameMode,
        val level: Int
)

enum class GameMode {
    Easy, Normal, Hard
}