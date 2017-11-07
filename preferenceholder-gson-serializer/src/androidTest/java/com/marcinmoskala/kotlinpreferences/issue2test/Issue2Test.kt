package com.marcinmoskala.kotlinpreferences.issue2test

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import com.marcinmoskala.kotlinpreferences.gson.GsonSerializer
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class Issue2Test {

    data class User(var name: String, var age: Int = 0)

    object AppPreference : PreferenceHolder() {
        var users: List<User>? by bindToPreferenceFieldNullable()
    }

    init {
        PreferenceHolder.setContext(InstrumentationRegistry.getTargetContext())
        PreferenceHolder.serializer = GsonSerializer(Gson())
    }

    @Test
    fun booleanDefaultChangeTest() {
        val users = List(6) { i -> User("Name$i", i) }
        AppPreference.users = users
        AppPreference.clearCache()
        val readUsers = AppPreference.users
        Assert.assertEquals(users, readUsers)
    }
}
