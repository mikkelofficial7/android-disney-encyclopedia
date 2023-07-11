package com.ewide.test.mikkel.di

import android.content.Context
import android.content.SharedPreferences

class SharedPrefModule {
    companion object {
        fun providePreference(context: Context) : SharedPreferences {
            return context.getSharedPreferences("SharedPreferenceEwide", Context.MODE_PRIVATE)
        }
    }
}