package com.example.repository.local

import com.example.model.DataModel

import javax.sql.DataSource

class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()
): com.example.repository.DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> =
        remoteProvider.getData(word)
}