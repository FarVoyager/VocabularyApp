package com.example.core


interface IHistoryInteractor<T> {
    suspend fun getData(): T
    suspend fun getDataByQuery(query: String): T



}