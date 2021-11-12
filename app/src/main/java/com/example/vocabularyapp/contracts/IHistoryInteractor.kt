package com.example.vocabularyapp.contracts

import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.model.DataModel

interface IHistoryInteractor<T> {
    suspend fun getData(): T
    suspend fun getDataByQuery(query: String): T



}