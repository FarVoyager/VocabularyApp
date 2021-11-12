package com.example.vocabularyapp.model.local

import com.example.vocabularyapp.contracts.DataSource
import com.example.vocabularyapp.model.DataModel
import com.example.vocabularyapp.model.Meanings
import com.example.vocabularyapp.model.Translation
import com.example.vocabularyapp.model.local.room.Database
import io.reactivex.Observable
import javax.inject.Inject

class RoomDataBaseImplementation() : DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        TODO("Not yet implemented")
    }

}