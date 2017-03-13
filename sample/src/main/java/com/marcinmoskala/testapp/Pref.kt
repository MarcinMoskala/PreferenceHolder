package com.marcinmoskala.testapp

import com.marcinmoskala.kotlinpreferences.PreferenceHolder

object Pref : PreferenceHolder() {
    var text: String? by bindToPreferenceFieldNullable()
    var num: Int by bindToPreferenceField(0, "SomeIntKey")
}

object OtherPref : PreferenceHolder() {
    var together: Together? by bindToPreferenceFieldNullable("SomeOtherKey")

    data class Together(
            val text: String,
            val num: Int?
    )
}