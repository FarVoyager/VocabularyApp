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

class MainInteractor @Inject constructor (
    // Снабжаем интерактор репозиторием для получения локальных или внешних данных
    @Named(NAME_REMOTE) val remoteRepository: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val localRepository: Repository<List<DataModel>>
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