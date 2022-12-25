package com.gultendogan.pixabayapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gultendogan.pixabayapp.data.entity.Hit

@Database(entities = [Hit::class], version = 1, exportSchema = false)
abstract class PixabayDatabase : RoomDatabase() {
    abstract fun pixebayDao(): PixabayDao
}