package com.ewide.test.mikkel.base.sharedpreference

import android.content.SharedPreferences

class BaseSharedPreferences(var sharedPreferences: SharedPreferences) {
    val editor: SharedPreferences.Editor by lazy {
        sharedPreferences.edit()
    }

    fun <T>saveData(key: String, value: T) {
        when(value) {
           is String -> editor.putString(key, value)
           is Int -> editor.putInt(key, value)
           is Float -> editor.putFloat(key, value)
           is Boolean -> editor.putBoolean(key, value)
           is Long -> editor.putLong(key, value)
        }
        editor.commit()
    }

    inline fun <reified T> getData(key: String, defaultValue: T) : T {
        return when (defaultValue) {
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue as Boolean) as T
            is Int -> sharedPreferences.getInt(key, defaultValue as Int) as T
            is Float -> sharedPreferences.getFloat(key, defaultValue as Float) as T
            is String -> sharedPreferences.getString(key, defaultValue as String) as T
            is Long -> sharedPreferences.getLong(key, defaultValue as Long) as T
            else -> throw Exception("Unhandled return type")
        }
    }

    fun clearAllPreference() {
        editor.clear()
        editor.commit()
    }
}