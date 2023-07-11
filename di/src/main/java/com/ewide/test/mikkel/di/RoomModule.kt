package com.ewide.test.mikkel.di

import android.content.Context
import androidx.room.Room
import com.ewide.test.mikkel.room.DBConfig

class RoomModule {
    companion object {
        fun provideRoom(context: Context) : DBConfig {
            return Room.databaseBuilder(context, DBConfig::class.java, "db_ewide")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}