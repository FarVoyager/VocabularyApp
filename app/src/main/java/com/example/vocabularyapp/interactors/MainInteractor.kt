package com.example.vocabularyapp.interactors

import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.contracts.Interactor
import com.example.vocabularyapp.contracts.Repository
import com.example.vocabularyapp.model.DataModel
import io.reactivex.Observable

class MainInteractor(
    // Снабжаем интерактор репозиторием для получения локальных или внешних данных
private val remoteRepository: Repository<List<DataModel>>,
private val localRepository: Repository<List<DataModel>>
): Interactor<AppState> {
    // Интерактор лишь запрашивает у репозитория данные, детали имплементации интерактору неизвестны
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}