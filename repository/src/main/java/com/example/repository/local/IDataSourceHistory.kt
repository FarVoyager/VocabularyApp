package com.example.repository.local

import com.example.model.DataModel

interface IDataSourceHistory<T> {
    suspend fun getData(): T
    suspend fun insertData(data: List<DataModel>?)
    suspend fun getDataByQuery(query: String): DataModel

}