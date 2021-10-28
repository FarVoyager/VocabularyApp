package com.example.vocabularyapp.model.remote

import com.example.vocabularyapp.contracts.DataSource
import com.example.vocabularyapp.model.DataModel
import io.reactivex.Observable

class DataSourceRemote(private val remoteProvider: RetrofitImplementation =
        RetrofitImplementation()):
        DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> =
        remoteProvider.getData(word)
}