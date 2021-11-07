package com.example.vocabularyapp.contracts

import io.reactivex.Observable

interface Repository<T> {
    suspend fun getData(word: String): T
}