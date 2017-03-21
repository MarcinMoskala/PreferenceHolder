package com.marcinmoskala.kotlinpreferences.purejunit

import android.content.Context
import com.marcinmoskala.kotlinpreferences.PreferenceHolder

interface IOverridablePrefenences {
    var canEatPie: Boolean
    var pieBaked: Int
    var allPieInTheWorld: Long
    var pieEaten: Float
    var bestPieName: String
}

class OverridablePrefenences: PreferenceHolder(), IOverridablePrefenences {
    override var canEatPie: Boolean by bindToPropertyWithBackup(true)
    override var pieBaked: Int by bindToPropertyWithBackup(5)
    override var allPieInTheWorld: Long by bindToPropertyWithBackup(-1L)
    override var pieEaten: Float by bindToPropertyWithBackup(0.0F)
    override var bestPieName: String by bindToPropertyWithBackup("Pie")

    companion object : Provider<IOverridablePrefenences>({ OverridablePrefenences() })
}

class SomeDomainClass() {

    private val pref by OverridablePrefenences.lazyGet()

    fun pieICanEat() = pref.allPieInTheWorld
}

abstract class Provider<T>(val initializer: () -> T) {
    var override: T? = null
    var original: T? = null

    fun get(): T = override ?: original ?: initializer().apply { original = this }
    fun lazyGet(): Lazy<T> = lazy { get() }
}
