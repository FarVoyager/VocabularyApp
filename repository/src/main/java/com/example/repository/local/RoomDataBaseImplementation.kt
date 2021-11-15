package com.example.repository.local

import com.example.model.DataModel
import com.example.repository.DataSource

class RoomDataBaseImplementation() : DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        TODO("Not yet implemented")
    }

}