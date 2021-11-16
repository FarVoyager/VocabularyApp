package com.example.repository.local

import com.example.model.DataModel

class DataSourceHistory(private val historyProvider: IDataSourceHistory<List<DataModel>>): IDataSourceHistory<List<DataModel>> {
    override suspend fun getData(): List<DataModel> = historyProvider.getData()
    override suspend fun insertData(data: List<DataModel>?) {
        historyProvider.insertData(data)
    }
    override suspend fun getDataByQuery(query: String): DataModel = historyProvider.getDataByQuery(query)
}