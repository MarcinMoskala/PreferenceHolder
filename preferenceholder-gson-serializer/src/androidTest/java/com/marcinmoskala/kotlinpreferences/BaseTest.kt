package com.marcinmoskala.kotlinpreferences

import android.support.test.InstrumentationRegistry
import com.google.gson.Gson
import com.marcinmoskala.kotlinpreferences.collections.CollectionsPref
import com.marcinmoskala.kotlinpreferences.gson.GsonSerializer
import com.marcinmoskala.kotlinpreferences.objects.ComplexTestPreferences

open class GsonBaseTest {

    init {
        PreferenceHolder.setContext(InstrumentationRegistry.getTargetContext())
        PreferenceHolder.serializer = GsonSerializer(Gson())
        ComplexTestPreferences.clear()
        CollectionsPref.clear()
    }
}