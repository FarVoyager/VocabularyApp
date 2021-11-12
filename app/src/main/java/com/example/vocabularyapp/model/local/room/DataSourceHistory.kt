package com.example.vocabularyapp.model.local.room

import com.example.vocabularyapp.contracts.DataSource
import com.example.vocabularyapp.model.DataModel

class DataSourceHistory(private val historyProvider: IDataSourceHistory<List<DataModel>>): IDataSourceHistory<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> = historyProvider.getData(word)
    override suspend fun insertData(data: List<DataModel>?) {
        historyProvider.insertData(data)
    }
}