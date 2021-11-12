package com.example.vocabularyapp.model.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 1, exportSchema = true)
abstract class Database: RoomDatabase() {
    abstract val wordDao: WordDao
}