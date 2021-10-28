package com.example.vocabularyapp.model

import com.example.vocabularyapp.contracts.DataSource
import com.example.vocabularyapp.contracts.Repository
import io.reactivex.Observable

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>): Repository<List<DataModel>>  {
    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}