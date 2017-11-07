package com.marcinmoskala.kotlinpreferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.marcinmoskala.kotlinpreferences.bindings.Clearable
import com.marcinmoskala.kotlinpreferences.bindings.PreferenceFieldBinder
import com.marcinmoskala.kotlinpreferences.bindings.PreferenceFieldBinderNullable
import java.lang.reflect.Type
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

abstract class PreferenceHolder {

    protected inline fun <reified T : Any> bindToPreferenceField(default: T, key: String? = null): ReadWriteProperty<PreferenceHolder, T>
            = bindToPreferenceField(T::class, object : TypeToken<T>() {}.type, default, key)

    protected inline fun <reified T : Any> bindToPreferenceFieldNullable(key: String? = null): ReadWriteProperty<PreferenceHolder, T?>
            = bindToPreferenceFieldNullable(T::class, object : TypeToken<T>() {}.type, key)

    protected fun <T : Any> bindToPreferenceField(clazz: KClass<T>, type: Type, default: T, key: String?): ReadWriteProperty<PreferenceHolder, T>
            = PreferenceFieldBinder(clazz, type, default, key)

    protected fun <T : Any> bindToPreferenceFieldNullable(clazz: KClass<T>, type: Type, key: String?): ReadWriteProperty<PreferenceHolder, T?>
            = PreferenceFieldBinderNullable(clazz, type, key)

    /**
     *  Function used to clear all SharedPreference and PreferenceHolder data. Useful especially
     *  during tests or when implementing Logout functionality.
     */
    fun clear() {
        forEachDelegate { delegate, property ->
            delegate.clear(this, property)
        }
    }

    fun clearCache() {
        forEachDelegate { delegate, property ->
            delegate.clearCache()
        }
    }

    private fun forEachDelegate(f: (Clearable, KProperty<*>) -> Unit) {
        val pref = preferences ?: return
        val properties = this::class.declaredMemberProperties
                .filterIsInstance<KProperty1<SharedPreferences, *>>()
        for (p in properties) {
            val prevAccessible = p.isAccessible
            if (!prevAccessible) p.isAccessible = true
            val delegate = p.getDelegate(pref)
            if (delegate is Clearable) f(delegate, p)
            p.isAccessible = prevAccessible
        }
    }

    companion object {

        /**
         *  This property should be used to set serializer to use types that are not supported by SharedPreference.
         */
        var serializer: Serializer? = null

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
    }
}