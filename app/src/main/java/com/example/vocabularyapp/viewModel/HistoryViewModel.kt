package com.example.vocabularyapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.contracts.IHistoryInteractor
import com.example.vocabularyapp.contracts.IMainInteractor
import kotlinx.coroutines.*

class HistoryViewModel(private val interactor: IHistoryInteractor<AppState>): BaseViewModel<AppState>() {
    private val liveData: MutableLiveData<AppState> = MutableLiveData()
    private var jobGetData: Job? = null

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    override fun subscribe(): LiveData<AppState> {
        return liveData
    }

    override fun getData(word: String, isOnline: Boolean) {
        liveData.postValue(AppState.Loading(null))
        jobGetData?.cancel()
        // Запускаем корутину для асинхронного доступа к серверу с помощью launch
        jobGetData = viewModelCoroutineScope.launch {
            liveData.postValue(interactor.getData())
        }
    }

    private fun handleError(error: Throwable) {
        liveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        liveData.postValue(AppState.Success(null))
        super.onCleared()
        viewModelCoroutineScope.cancel()
    }
}