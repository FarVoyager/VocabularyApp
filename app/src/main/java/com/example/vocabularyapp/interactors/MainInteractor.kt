package com.example.vocabularyapp.interactors

import com.example.core.IMainInteractor
import com.example.model.AppState
import com.example.model.DataModel
import com.example.repository.Repository
import com.example.repository.local.IDataSourceHistory

class MainInteractor (
    // Снабжаем интерактор репозиторием для получения локальных или внешних данных
    val remoteRepository: Repository<List<DataModel>>,
    val localRepository: Repository<List<DataModel>>,
    val roomHistoryRepository: IDataSourceHistory<List<DataModel>>
): IMainInteractor<AppState> {
    // Интерактор лишь запрашивает у репозитория данные, детали имплементации интерактору неизвестны
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) { remoteRepository }
            else { localRepository }
                .getData(word)
        )
    }

    override suspend fun insertData(data: List<DataModel>?) {
        roomHistoryRepository.insertData(data)
    }


}