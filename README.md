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

It it suggested to do it in project Application class, but it can also be done in BaseActivity or just first activity starting app. As an alternative, there can also be added PreferenceHolderApplication as an name in application in AndroidManifest: ([example](https://github.com/MarcinMoskala/PreferenceHolder/blob/master/sample/src/main/AndroidManifest.xml#L12))

```
android:name=".PreferenceHolderApplication"
```

## Install

To add PreferenceHolder to the project, add in build.gradle file:

```groovy
dependencies {
    compile 'com.github.MarcinMoskala:PreferenceHolder:1.1-beta.1'
}
```

And while library is located on JitPack, remember to add on module build.gradle (unless you already have it):

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```
