package com.marcinmoskala.kotlinpreferences

import android.content.Context
import android.content.SharedPreferences

var SharedPreferences.canEatPie: Boolean by bindToPreferenceField(true)
var SharedPreferences.pieBaked: Int by bindToPreferenceField(0)
var SharedPreferences.allPieInTheWorld: Long by bindToPreferenceField(0)
var SharedPreferences.pieEaten: Float by bindToPreferenceField(0.0F)
var SharedPreferences.bestPieName: String by bindToPreferenceField("Pie")

var SharedPreferences.isMonsterKiller: Boolean? by bindToPreferenceFieldNullable()
var SharedPreferences.monstersKilled: Int? by bindToPreferenceFieldNullable()
var SharedPreferences.numberOfHahaInLough: Long? by bindToPreferenceFieldNullable()
var SharedPreferences.experience: Float? by bindToPreferenceFieldNullable()
var SharedPreferences.className: String? by bindToPreferenceFieldNullable()

var SharedPreferences.character: Character? by bindToPreferenceFieldNullable()
var SharedPreferences.savedGame: Game? by bindToPreferenceFieldNullable()

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