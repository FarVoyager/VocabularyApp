package com.example.repository.remote

import com.example.model.DataModel
import com.example.repository.DataSource


class DataSourceRemote (private val remoteProvider: RetrofitImplementation = RetrofitImplementation()
): DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> =
        remoteProvider.getData(word)
}