# KotlinPreferences
Kotlin Android Library, that makes preference usage simple and fun.

[![](https://jitpack.io/v/marcinmoskala/kotlinpreferences.svg)](https://jitpack.io/#marcinmoskala/kotlinpreferences)

With KotlinPreferences, all you can define different preference fields this way:

```kotlin
var SharedPreferences.canEatPie: Boolean by bindToPreferenceField(true)
```

And use it like as an extension form any SharedPreference object:

```kotlin
if(preferences.canEatPie) //...
```

Here are other preference variants: (see [full example](https://github.com/MarcinMoskala/KotlinPreferences/blob/master/kotlinpreferences-lib/src/androidTest/java/com/marcinmoskala/kotlinpreferences/ExampleConfig.kt) and [usage](https://github.com/MarcinMoskala/KotlinPreferences/tree/master/kotlinpreferences-lib/src/androidTest/java/com/marcinmoskala/kotlinpreferences))

```kotlin
var SharedPreferences.canEatPie: Boolean by bindToPreferenceField(true)
var SharedPreferences.allPieInTheWorld: Long by bindToPreferenceField(0)

var SharedPreferences.isMonsterKiller: Boolean? by bindToPreferenceFieldNullable()
var SharedPreferences.monstersKilled: Int? by bindToPreferenceFieldNullable()
var SharedPreferences.experience: Float? by bindToPreferenceFieldNullable()
var SharedPreferences.className: String? by bindToPreferenceFieldNullable()

var SharedPreferences.character: Character? by bindToPreferenceFieldNullable()
var SharedPreferences.savedGame: Game? by bindToPreferenceFieldNullable()
```

## Install

To add KotlinPreferences to the project, add in build.gradle file:

```groovy
dependencies {
    compile 'com.github.marcinmoskala:kotlinpreferences:0.03'
}
```

And while library is located on JitPack, remember to add on module build.gradle (unless you already have it):

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```