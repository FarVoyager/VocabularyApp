package com.example.vocabularyapp.interactors

import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.contracts.IHistoryInteractor
import com.example.vocabularyapp.contracts.IMainInteractor
import com.example.vocabularyapp.model.DataModel
import com.example.vocabularyapp.model.local.room.Database
import com.example.vocabularyapp.model.local.room.IDataSourceHistory

class HistoryInteractor(
    private val roomHistoryRepository: IDataSourceHistory<List<DataModel>>
): IHistoryInteractor<AppState> {
    override suspend fun getData(): AppState {
        return AppState.Success(
            roomHistoryRepository.getData()
        )
    }

    override suspend fun getDataByQuery(query: String): AppState =
        AppState.Success(listOf(roomHistoryRepository.getDataByQuery(query)))
}