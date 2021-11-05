package com.example.vocabularyapp.contracts

import io.reactivex.Observable

interface DataSource<T> {
    suspend fun getData(word: String): T
}