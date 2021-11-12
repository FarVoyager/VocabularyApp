package com.example.vocabularyapp.model.local.room

import com.example.vocabularyapp.model.DataModel

interface IDataSourceHistory<T> {
    suspend fun getData(word: String): T
    suspend fun insertData(data: List<DataModel>?)
}