package com.example.vocabularyapp.contracts

import io.reactivex.Observable

interface Repository<T> {
    fun getData(word: String): Observable<T>
}