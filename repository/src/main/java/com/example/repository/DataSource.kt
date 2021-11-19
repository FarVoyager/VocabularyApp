package com.example.repository

import io.reactivex.Observable

interface DataSource<T> {
    suspend fun getData(word: String): T
}