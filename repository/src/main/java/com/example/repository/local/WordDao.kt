package com.example.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordDao {
    @Query("SELECT * FROM Word")
    fun getAll(): List<Word>

    @Query("SELECT * FROM Word WHERE word = :query")
    fun getByWord(query: String): Word?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg words: Word)
}