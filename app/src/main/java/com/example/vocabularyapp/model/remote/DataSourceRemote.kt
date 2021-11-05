package com.example.vocabularyapp.model.remote

import com.example.vocabularyapp.contracts.DataSource
import com.example.vocabularyapp.model.DataModel
import io.reactivex.Observable
import javax.inject.Inject

class DataSourceRemote (private val remoteProvider: RetrofitImplementation = RetrofitImplementation()
): DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> =
        remoteProvider.getData(word)
}