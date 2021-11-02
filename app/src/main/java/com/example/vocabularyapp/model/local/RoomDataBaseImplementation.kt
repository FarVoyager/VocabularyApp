package com.example.vocabularyapp.model.local

import com.example.vocabularyapp.contracts.DataSource
import com.example.vocabularyapp.model.DataModel
import io.reactivex.Observable
import javax.inject.Inject

class RoomDataBaseImplementation: DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("Not yet implemented")
    }
}