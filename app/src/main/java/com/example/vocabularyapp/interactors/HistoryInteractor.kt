package com.example.vocabularyapp.interactors

import com.example.core.IHistoryInteractor
import com.example.model.AppState
import com.example.model.DataModel
import com.example.repository.local.IDataSourceHistory

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