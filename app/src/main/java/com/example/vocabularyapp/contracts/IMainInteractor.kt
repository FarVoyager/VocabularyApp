package com.example.vocabularyapp.contracts

import com.example.vocabularyapp.model.DataModel
import io.reactivex.Observable


interface IMainInteractor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
    suspend fun insertData(data: List<DataModel>?)
}