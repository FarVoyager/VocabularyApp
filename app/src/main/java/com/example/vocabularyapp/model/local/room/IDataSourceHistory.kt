package com.example.vocabularyapp.model.local.room

import com.example.vocabularyapp.model.DataModel

interface IDataSourceHistory<T> {
    suspend fun getData(): T
    suspend fun insertData(data: List<DataModel>?)
    suspend fun getDataByQuery(query: String): DataModel

}