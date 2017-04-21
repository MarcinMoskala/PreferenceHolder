package com.marcinmoskala.kotlinpreferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.Preference
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.marcinmoskala.kotlinpreferences.bindings.PreferenceFieldBinder
import com.marcinmoskala.kotlinpreferences.bindings.PreferenceFieldBinderNullable
import java.lang.reflect.Type
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberExtensionProperties
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

abstract class PreferenceHolder {

    protected inline fun <reified T : Any> bindToPreferenceField(default: T, key: String? = null): ReadWriteProperty<PreferenceHolder, T>
            = bindToPreferenceField(T::class, default, object : TypeToken<T>() {}.type, key)

    protected inline fun <reified T : Any> bindToPreferenceFieldNullable(key: String? = null): ReadWriteProperty<PreferenceHolder, T?>
            = bindToPreferenceFieldNullable(T::class, object : TypeToken<T>() {}.type, key)

    protected fun <T : Any> bindToPreferenceField(clazz: KClass<T>, default: T, type: Type, key: String?): ReadWriteProperty<PreferenceHolder, T>
            = PreferenceFieldBinder(clazz, default, type, key)

    protected fun <T : Any> bindToPreferenceFieldNullable(clazz: KClass<T>, type: Type, key: String?): ReadWriteProperty<PreferenceHolder, T?>
            = PreferenceFieldBinderNullable(clazz, type, key)

    /**
     *  Function used to clear all SharedPreference and PreferenceHolder data. Useful especially
     *  during tests or when implementing Logout functionality.
     */
    fun clear() {
        val pref = preferences ?: return
        val properties = this::class.declaredMemberProperties
                .filterIsInstance<KProperty1<SharedPreferences, *>>()
        for (p in properties) {
            val prevAccessible = p.isAccessible
            if(!prevAccessible) p.isAccessible = true
            val delegate = p.getDelegate(pref)
            when (delegate) {
                is PreferenceFieldBinder<*> -> delegate.clear(p)
                is PreferenceFieldBinderNullable<*> -> delegate.clear(p)
            }
            p.isAccessible = prevAccessible
        }
    }

    companion object {

        /**
         *  This property can be used to set custom Gson for preferences holding. It is usefull when
         *  there are complex objects holden in objects that we want to save on preferences.
         *  Common use case is when we have DateTime or LocalDate from JodaTime objects which needs
         *  special Gson converters, to be able to be converted to String by Gson.
         */
        var preferencesGson: Gson = GsonBuilder().create()

        /**
         *  When testing mode is turned on, then there is no need to provide Context, and all bindings
         *  are acting just like standard Kotln fields. If we then need to mock some situation then all
         *  we need to do is to set values on Preferences. Example:
         *
         *  fun newUserCreationTest() {
         *      PreferenceHolder.testingMode = true
         *      UserPreferences.user = null
         *      val mockedView = object : UserView {
         *         fun getName() = "Marcin"
         *         gun getSurname() = "Moskala"
         *      }
         *      val presenter = UserPresenter(mockedView)
         *      presenter.createUser()
         *      assert(User("Marcin", "Moskala"), UserPreferences.user)
         *  }
         */
        var testingMode: Boolean = false

        /**
         *  It should be used to set ApplicationContext on project Application class. Only case when
         *  it could be ommitted is when testingMode is turned on.
         */
        fun setContext(context: Context) {
            preferences = PreferenceManager.getDefaultSharedPreferences(context)
        }

        private const val noPreferencesSetErrorText = "No preferences in PreferenceHolder instance. Add in Application class PreferenceHolder.setContext(applicationContext) or make PreferenceHolderApplication to be your project application class (android:name field in AndroidManifest)."

        internal var preferences: SharedPreferences? = null

        internal fun getPreferencesOrThrowError(): SharedPreferences = PreferenceHolder.preferences ?: throw Error(noPreferencesSetErrorText)

        fun clear() {
            // TODO
        }
    }
}