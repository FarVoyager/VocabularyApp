package com.example.vocabularyapp.model.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Word(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "word") val word: String?,
    @ColumnInfo(name = "translation") val translation: String?
)

