# PreferenceHolder
Kotlin Android Library, that makes preference usage simple and fun.

[![](https://jitpack.io/v/MarcinMoskala/PreferenceHolder.svg)](https://jitpack.io/#MarcinMoskala/PreferenceHolder)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
[![Build Status](https://travis-ci.org/MarcinMoskala/PreferenceHolder.svg?branch=master)](https://travis-ci.org/MarcinMoskala/PreferenceHolder)
[![codebeat badge](https://codebeat.co/badges/035647e2-1a10-48b8-80ae-e6a02c5c0ded)](https://codebeat.co/projects/github-com-marcinmoskala-preferenceholder-master)

This library younger brother of [KotlinPreferences](https://github.com/MarcinMoskala/KotlinPreferences).

With PreferenceHolder, you can define different preference fields this way:

```kotlin
object Pref: PreferenceHolder() {
    var canEatPie: Boolean by bindToPreferenceField(true)
}
```

And use it this way:

```kotlin
if(Pref.canEatPie) //...
```

Here are other preference definition examples: (see [full example](https://github.com/MarcinMoskala/PreferenceHolder/blob/master/kotlinpreferences-lib/src/androidTest/java/com/marcinmoskala/kotlinpreferences/ExampleConfig.kt) and [usage](https://github.com/MarcinMoskala/PreferenceHolder/tree/master/kotlinpreferences-lib/src/androidTest/java/com/marcinmoskala/kotlinpreferences))

```kotlin
object UserPref: PreferenceHolder() {
    var canEatPie: Boolean by bindToPreferenceField(true)
    var allPieInTheWorld: Long by bindToPreferenceField(0)

    var isMonsterKiller: Boolean? by bindToPreferenceFieldNullable()
    var monstersKilled: Int? by bindToPreferenceFieldNullable()
    
    // Property with backup is reading stored value in the first usage, 
    // and saving it, in background, each time it is changed.
    var experience: Float? by bindToPropertyWithBackup(-1.0F) 
    var className: String? by bindToPropertyWithBackupNullable()

    // Each type, that can be serialized and deserialized using Gson, 
    // can be used as type of property binded to SharedPreference field.
    var character: Character? by bindToPreferenceFieldNullable()
    var savedGame: Game? by bindToPreferenceFieldNullable()

    // Single level collections are also supported since 1.2
    var longList: Map<Int, Long> by bindToPreferenceField(mapOf(0 to 12L, 10 to 143L))
    var propTest: List<Character>? by bindToPropertyWithBackupNullable()
    var elemTest: Set<Elems> by bindToPreferenceField(setOf(Elems.Elem1, Elems.Elem3))
}
```

There must be application Context added to PreferenceHolder companion object. Example:

```kotlin
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        PreferenceHolder.setContext(applicationContext)
    }
}
```

It it suggested to do it in project Application class. As an alternative, there can also be added PreferenceHolderApplication as an name in application in AndroidManifest: ([example](https://github.com/MarcinMoskala/PreferenceHolder/blob/master/sample/src/main/AndroidManifest.xml#L12))

```
android:name="com.marcinmoskala.kotlinpreferences.PreferenceHolderApplication"
```

## Unit testing components

Library also include test mode:

```
PreferenceHolder.testingMode = true
```

When it is turned on, then all properties are acting just like normal properties without binding to preference field. This allows to make unit tests to presenters and use cases that are using instance of PreferenceHolder.

## Install

To add PreferenceHolder to the project, add in build.gradle file:

```groovy
dependencies {
    compile 'com.github.marcinmoskala:PreferenceHolder:1.3'
}
```

And while library is located on JitPack, remember to add on module build.gradle (unless you already have it):

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```
