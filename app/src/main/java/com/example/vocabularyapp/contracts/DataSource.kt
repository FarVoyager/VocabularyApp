package com.example.vocabularyapp.contracts

import io.reactivex.Observable

interface DataSource<T> {
    fun getData(word: String): Observable<T>
}