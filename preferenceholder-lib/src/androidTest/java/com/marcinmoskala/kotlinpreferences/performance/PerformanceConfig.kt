package com.marcinmoskala.kotlinpreferences.performance

import com.marcinmoskala.kotlinpreferences.PreferenceHolder

object ComparePreferences: PreferenceHolder() {
    var f: Int by bindToPreferenceField(0)
    var p: Int by bindToPropertyWithBackup(0)

    var fl: ListPackage? by bindToPreferenceFieldNullable()
    var pl: ListPackage? by bindToPropertyWithBackupNullable()
}

data class ListPackage(val list: List<Int>)
