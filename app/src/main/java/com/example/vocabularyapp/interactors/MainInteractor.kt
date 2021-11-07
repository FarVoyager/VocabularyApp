package com.example.vocabularyapp.interactors

import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.contracts.Interactor
import com.example.vocabularyapp.contracts.Repository
import com.example.vocabularyapp.di.NAME_LOCAL
import com.example.vocabularyapp.di.NAME_REMOTE
import com.example.vocabularyapp.model.DataModel
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

class MainInteractor (
    // Снабжаем интерактор репозиторием для получения локальных или внешних данных
    val remoteRepository: Repository<List<DataModel>>,
    val localRepository: Repository<List<DataModel>>
): Interactor<AppState> {
    // Интерактор лишь запрашивает у репозитория данные, детали имплементации интерактору неизвестны
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) { remoteRepository }
            else { localRepository }
                .getData(word)
        )
    }

}