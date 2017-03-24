package com.marcinmoskala.kotlinpreferences

open class JunitBaseTest {

    init {
        PreferenceHolder.testingMode = true
    }
}