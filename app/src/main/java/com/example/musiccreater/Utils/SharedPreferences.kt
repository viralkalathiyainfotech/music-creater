package com.example.musiccreater.Utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import kotlin.isInitialized
import kotlin.jvm.java


object SharedPreferences {
    private lateinit var sharedPref: SharedPreferences
    const val KEY_TOKEN = "token"
    const val KEY_ROLE = "role"
    const val KEY_IS_LOGGED_IN = "IS_LOGGED_IN"
    const val KEY_DROP_LAT = "DROP_LAT"
    const val KEY_DROP_LNG = "DROP_LNG"

    val gson = Gson()

    fun init(context: Context) {
        if (!::sharedPref.isInitialized) {
            sharedPref = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        }
    }

    fun putString(key: String, value: String?) {
        sharedPref.edit { putString(key, value) }
    }

    //    fun getString(key: String, default: String? = null, string: String): String? = sharedPref.getString(key, default)
    fun getString(key: String, default: String? = null): String? =
        sharedPref.getString(key, default)

    fun putBoolean(key: String, value: Boolean) {
        sharedPref.edit { putBoolean(key, value) }
    }

    fun getBoolean(key: String, default: Boolean = false): Boolean =
        sharedPref.getBoolean(key, default)

    fun clearPreferences() {
        sharedPref.edit { clear() }
    }

    fun removePreference(key: String) {
        sharedPref.edit { remove(key) }
    }
    //    // Save any single model
    inline fun <reified T> saveModel(key: String, model: T) {
        val json = gson.toJson(model)
        putString(key, json)
    }

    // Save List
    fun <T> saveList(key: String, list: List<T>) {
        val json = gson.toJson(list)
        sharedPref.edit().putString(key, json).apply()
    }
    // Save Double
    fun putDouble(key: String, value: Double) {
        sharedPref.edit().putString(key, value.toString()).apply()
    }

    inline fun <reified T> getModel(key: String): T? {
        val json = getString(key) ?: return null
        return try { gson.fromJson(json, T::class.java) } catch (_: Exception) { null }
    }
}