package com.ewide.test.mikkel.base.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map

class BasePreferenceDataStore(val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    suspend fun <T>readData(key: String, defaultValue : T, onFlow: (T) -> Unit) {
       val flowDataStore: Flow<T> =  when(defaultValue) {
            is Int -> {
                context.dataStore.data
                    .map { preferences ->
                        preferences[intPreferencesKey(key)] ?: 0
                    } as Flow<T>
            }
            is Float -> {
                context.dataStore.data
                    .map { preferences ->
                        preferences[floatPreferencesKey(key)] ?: 0f
                    } as Flow<T>
            }
            is Long -> {
                context.dataStore.data
                    .map { preferences ->
                        preferences[longPreferencesKey(key)] ?: 0L
                    } as Flow<T>
            }
            is Boolean -> {
                context.dataStore.data
                    .map { preferences ->
                        preferences[booleanPreferencesKey(key)] ?: false
                    } as Flow<T>
            }
            is String -> {
                context.dataStore.data
                    .map { preferences ->
                        preferences[stringPreferencesKey(key)] ?: ""
                    } as Flow<T>
            }
           else -> {
               throw Exception("unhandled data store")
           }
       }

       flowDataStore.collectLatest {
           onFlow(it)
       }
    }

    suspend fun <T>writeData(key: String, value: T) {
        when(value) {
            is Int -> {
                context.dataStore.edit { settings ->
                    settings[intPreferencesKey(key)] = value
                }
            }
            is Float -> {
                context.dataStore.edit { settings ->
                    settings[floatPreferencesKey(key)] = value
                }
            }
            is Boolean -> {
                context.dataStore.edit { settings ->
                    settings[booleanPreferencesKey(key)] = value
                }
            }
            is String -> {
                context.dataStore.edit { settings ->
                    settings[stringPreferencesKey(key)] = value
                }
            }
            is Long -> {
                context.dataStore.edit { settings ->
                    settings[longPreferencesKey(key)] = value
                }
            }
            else -> throw Exception("Unhandled data store")
        }
    }
}