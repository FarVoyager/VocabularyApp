package com.example.vocabularyapp.model

import com.example.vocabularyapp.contracts.DataSource
import com.example.vocabularyapp.contracts.Repository
import io.reactivex.Observable
import javax.inject.Inject

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>): Repository<List<DataModel>>  {
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}