package com.example.core

import com.example.model.DataModel


interface IMainInteractor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
    suspend fun insertData(data: List<DataModel>?)
}