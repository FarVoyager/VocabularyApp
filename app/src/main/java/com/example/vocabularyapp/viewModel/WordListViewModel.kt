package com.example.vocabularyapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.contracts.Interactor
import com.example.vocabularyapp.di.App
import com.example.vocabularyapp.model.DataModel
import kotlinx.coroutines.*

class WordListViewModel(
    private val mainInteractor: Interactor<AppState>,
    private val historyInteractor: Interactor<AppState>,

    ): BaseViewModel<AppState>() {

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
            val data = mainInteractor.getData(word, isOnline)
            liveData.postValue(data)
            when (data) {
                is AppState.Success -> {
                    println("BEB")
                    mainInteractor.insertData(data.data)
                }
                else -> {  }
            }

        }
    }

     private fun handleError(error: Throwable) {
        liveData.postValue(AppState.Error(error))
    }

//    private fun searchWordInHistory(text: String): DataModel {
//        //
//    }

    override fun onCleared() {
        liveData.postValue(AppState.Success(null))
        super.onCleared()
        viewModelCoroutineScope.cancel()
    }
}