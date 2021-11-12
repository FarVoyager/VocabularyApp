package com.example.vocabularyapp.model.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vocabularyapp.model.DataModel

@Dao
interface WordDao {
    @androidx.room.Query("SELECT * FROM Word")
    fun getAll(): List<Word>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg words: Word)
}