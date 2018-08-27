package com.marcinmoskala.kotlinpreferences

import android.content.SharedPreferences
import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertEquals
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class ListenerTest: BaseTest() {

    @Before
    fun setup() {
        PreferenceHolder.deregisterAllPreferenceFieldChangeListeners()
    }

    @Test
    fun singleLambdaListenerTest() {
        var count = 0
        val future = CompletableFuture<Int>()
        PreferenceHolder.registerPreferenceFieldChangeListener {
            _, _ -> if (++count == 2) future.complete(count)
        }
        TestPreferences.canEatPie = false
        TestPreferences.canEatPie = true
        assertEquals(2, future.get(5, TimeUnit.SECONDS))
    }

    @Test
    fun removeListenerTest() {
        val defaultListener = SharedPreferences.OnSharedPreferenceChangeListener { _, _ -> }
        PreferenceHolder.registerPreferenceFieldChangeListener(defaultListener)
        assertEquals(1, PreferenceHolder.listeners.size)

        PreferenceHolder.deregisterPreferenceFieldChangeListener(defaultListener)
        assertEquals(0, PreferenceHolder.listeners.size)
    }

    @Test
    fun removeAllListenersTest() {
        for (i in 1..5)
            PreferenceHolder.registerPreferenceFieldChangeListener { _, _ -> }
        assertEquals(5, PreferenceHolder.listeners.size)

        PreferenceHolder.deregisterAllPreferenceFieldChangeListeners()
        assertEquals(0, PreferenceHolder.listeners.size)
    }

}