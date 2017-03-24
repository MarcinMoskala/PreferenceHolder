package com.marcinmoskala.kotlinpreferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.marcinmoskala.kotlinpreferences.bindings.PreferenceBinding
import com.marcinmoskala.kotlinpreferences.bindings.PreferenceFieldBinder
import com.marcinmoskala.kotlinpreferences.bindings.PreferenceFieldBinderNullable
import java.lang.reflect.Type
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass

abstract class PreferenceHolder {

    protected inline fun <reified T : Any> bindToPreferenceField(default: T, key: String? = null): ReadWriteProperty<PreferenceHolder, T>
            = bindToPreferenceField(T::class, default, object : TypeToken<T>() {}.type, key)

    protected inline fun <reified T : Any> bindToPreferenceFieldNullable(key: String? = null): ReadWriteProperty<PreferenceHolder, T?>
            = bindToPreferenceFieldNullable(T::class, object : TypeToken<T>() {}.type, key)

    protected fun <T: Any> bindToPreferenceField(clazz: KClass<T>, default: T, type: Type, key: String?): ReadWriteProperty<PreferenceHolder, T>
            = PreferenceFieldBinder(clazz, default, type, key)

    protected fun <T: Any> bindToPreferenceFieldNullable(clazz: KClass<T>, type: Type, key: String?): ReadWriteProperty<PreferenceHolder, T?>
            = PreferenceFieldBinderNullable(clazz, type, key)

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

        /**
         *  Function used to clear all SharedPreference and PreferenceHolder data. Useful especially
         *  during tests or when implementing Logout functionality.
         */
        fun clear() {
            getPreferencesOrThrowError().edit().clear().apply()
            propertiesReference.forEach { it.clear() }
        }

        private const val noPreferencesSetErrorText = "No preferences in PreferenceHolder instance. Add in Application class PreferenceHolder.setContext(applicationContext) or make PreferenceHolderApplication to be your project application class (android:name field in AndroidManifest)."

        internal var preferences: SharedPreferences? = null

        internal fun getPreferencesOrThrowError(): SharedPreferences = PreferenceHolder.preferences ?: throw Error(noPreferencesSetErrorText)

        internal var propertiesReference: List<PreferenceBinding> = listOf()
    }
}