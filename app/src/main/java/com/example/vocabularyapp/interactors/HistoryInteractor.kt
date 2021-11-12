package com.example.vocabularyapp.interactors

import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.contracts.Interactor
import com.example.vocabularyapp.contracts.Repository
import com.example.vocabularyapp.model.DataModel
import com.example.vocabularyapp.model.local.room.IDataSourceHistory

class HistoryInteractor(
    private val roomHistoryRepository: IDataSourceHistory<List<DataModel>>
): Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            roomHistoryRepository.getData(word)
        )
    }

    override suspend fun insertData(data: List<DataModel>?) {
        println("Эта реализация интерактора не подразумевает сохранение в БД")
    }


}