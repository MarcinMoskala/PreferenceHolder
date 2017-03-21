package com.marcinmoskala.kotlinpreferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.marcinmoskala.kotlinpreferences.bindings.PreferenceFieldBinder
import com.marcinmoskala.kotlinpreferences.bindings.PreferenceFieldBinderNullable
import com.marcinmoskala.kotlinpreferences.bindings.PropertyWithBackup
import com.marcinmoskala.kotlinpreferences.bindings.PropertyWithBackupNullable
import java.lang.reflect.Type
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty

abstract class PreferenceHolder {

    protected inline fun <reified T : Any> bindToPreferenceField(default: T, key: String? = null): ReadWriteProperty<PreferenceHolder, T>
            = bindToPreferenceField(T::class, default, object : TypeToken<T>() {}.type, key)

    protected inline fun <reified T : Any> bindToPreferenceFieldNullable(key: String? = null): ReadWriteProperty<PreferenceHolder, T?>
            = bindToPreferenceFieldNullable(T::class, object : TypeToken<T>() {}.type, key)

    protected inline fun <reified T : Any> bindToPropertyWithBackup(default: T, key: String? = null): ReadWriteProperty<PreferenceHolder, T>
            = bindToPropertyWithBackup(T::class, default, object : TypeToken<T>() {}.type, key)

    protected inline fun <reified T : Any> bindToPropertyWithBackupNullable(key: String? = null): ReadWriteProperty<PreferenceHolder, T?>
            = bindToPropertyWithBackupNullable(T::class, object : TypeToken<T>() {}.type, key)

    protected fun <T : Any> bindToPreferenceField(clazz: KClass<T>, default: T, type: Type, key: String?): ReadWriteProperty<PreferenceHolder, T>
            = PreferenceFieldBinder(clazz, default, type, key)

    protected fun <T : Any> bindToPreferenceFieldNullable(clazz: KClass<T>, type: Type, key: String?): ReadWriteProperty<PreferenceHolder, T?>
            = PreferenceFieldBinderNullable(clazz, type, key)

    protected fun <T : Any> bindToPropertyWithBackup(clazz: KClass<T>, default: T, type: Type, key: String?): ReadWriteProperty<PreferenceHolder, T>
            = PropertyWithBackup(clazz, default, type, key)

    protected fun <T : Any> bindToPropertyWithBackupNullable(clazz: KClass<T>, type: Type, key: String?): ReadWriteProperty<PreferenceHolder, T?>
            = PropertyWithBackupNullable(clazz, type, key)

    companion object {

        internal lateinit var preferences: SharedPreferences

        var getOverride: ((classRef: PreferenceHolder, property: String) -> Any)? = null

        var setOverride: ((classRef: PreferenceHolder, property: String, value: Any?) -> Unit)? = null

        var preferencesGson: Gson = GsonBuilder().create()

        fun setContext(context: Context) {
            preferences = PreferenceManager.getDefaultSharedPreferences(context)
        }

        fun clear() = preferences.edit().clear().apply()
    }
}