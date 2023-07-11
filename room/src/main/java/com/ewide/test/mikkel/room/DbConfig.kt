package com.ewide.test.mikkel.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ewide.test.mikkel.model.local.ListCharacter

@Database(entities = [ListCharacter::class], version = 1, exportSchema = false)
abstract class DBConfig : RoomDatabase() {
    abstract fun dataDao(): DataDao
}