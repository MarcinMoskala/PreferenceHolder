package com.marcinmoskala.kotlinpreferences.collections

import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import com.marcinmoskala.kotlinpreferences.objects.Character

object CollectionsPref: PreferenceHolder() {
    var intList: List<Int>? by bindToPreferenceFieldNullable()

    var stringList: List<String> by bindToPreferenceField(listOf())
    var longList: Map<Int, Long> by bindToPreferenceField(mapOf(0 to 12L, 10 to 143L))

    var propTest: List<Character>? by bindToPreferenceFieldNullable()
    var elemTest: Set<Elems> by bindToPreferenceField(setOf(Elems.Elem1, Elems.Elem3))

    var multiLevelList: List<List<Int>>? by bindToPreferenceFieldNullable()
}

enum class Elems {
    Elem1,
    Elem2,
    Elem3,
    Elem4,
    Elem5
}