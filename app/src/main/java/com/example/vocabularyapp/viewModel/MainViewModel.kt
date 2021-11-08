package com.example.vocabularyapp.viewModel

import androidx.lifecycle.LiveData
import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.contracts.Interactor
import kotlinx.coroutines.*

class MainViewModel(
    private val interactor: Interactor<AppState>
): BaseViewModel<AppState>() {

    private var jobGetData: Job? = null

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    fun subscribe(): LiveData<AppState> {
        return liveData
    }

    override fun getData(word: String, isOnline: Boolean) {
        liveData.postValue(AppState.Loading(null))
        jobGetData?.cancel()
        // Запускаем корутину для асинхронного доступа к серверу с помощью launch
        jobGetData = viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, online: Boolean) =
            liveData.postValue(interactor.getData(word, online))

     private fun handleError(error: Throwable) {
        liveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        liveData.postValue(AppState.Success(null))
        super.onCleared()
        viewModelCoroutineScope.cancel()
    }
}