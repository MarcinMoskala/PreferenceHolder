package com.marcinmoskala.kotlinpreferences

object TestPreferences: PreferenceHolder() {
    var canEatPie: Boolean by bindToPreferenceField(true)
    var pieBaked: Int by bindToPreferenceField(5)
    var allPieInTheWorld: Long by bindToPreferenceField(-1L)
    var pieEaten: Float by bindToPreferenceField(0.0F)
    var bestPieName: String by bindToPreferenceField("Pie")

    var isMonsterKiller: Boolean? by bindToPreferenceFieldNullable()
    var monstersKilled: Int? by bindToPreferenceFieldNullable()
    var numberOfHahaInLough: Long? by bindToPreferenceFieldNullable()
    var experience: Float? by bindToPreferenceFieldNullable()
    var className: String? by bindToPreferenceFieldNullable()
}

object PropertiesPreferences: PreferenceHolder() {
    var canEatPie: Boolean by bindToPropertyWithBackup(true)
    var pieBaked: Int by bindToPropertyWithBackup(5)
    var allPieInTheWorld: Long by bindToPropertyWithBackup(-1L)
    var pieEaten: Float by bindToPropertyWithBackup(0.0F)
    var bestPieName: String by bindToPropertyWithBackup("Pie")

    var isMonsterKiller: Boolean? by bindToPropertyWithBackupNullable()
    var monstersKilled: Int? by bindToPropertyWithBackupNullable()
    var numberOfHahaInLough: Long? by bindToPropertyWithBackupNullable()
    var experience: Float? by bindToPropertyWithBackupNullable()
    var className: String? by bindToPropertyWithBackupNullable()
}