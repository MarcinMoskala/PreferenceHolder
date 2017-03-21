package com.marcinmoskala.kotlinpreferences.junit

import com.marcinmoskala.kotlinpreferences.PreferenceHolder

object JUnitPref1: PreferenceHolder() {

    var i: Int by bindToPreferenceField(-1)
}

object JUnitPref2: PreferenceHolder() {

    var i: Int by bindToPreferenceField(-1)
}