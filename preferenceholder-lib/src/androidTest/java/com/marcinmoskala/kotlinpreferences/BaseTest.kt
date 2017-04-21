package com.marcinmoskala.kotlinpreferences

import android.support.test.InstrumentationRegistry
import com.marcinmoskala.kotlinpreferences.collections.CollectionsPref
import com.marcinmoskala.kotlinpreferences.objects.ComplexTestPreferences

open class BaseTest {

    init {
        PreferenceHolder.setContext(InstrumentationRegistry.getTargetContext())
        TestPreferences.clear()
        ComplexTestPreferences.clear()
        CollectionsPref.clear()
    }
}