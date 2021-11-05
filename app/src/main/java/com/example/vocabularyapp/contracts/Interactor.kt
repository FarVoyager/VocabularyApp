package com.example.vocabularyapp.contracts

import io.reactivex.Observable


interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}