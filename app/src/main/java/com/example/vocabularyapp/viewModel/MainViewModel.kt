package com.example.vocabularyapp.viewModel

import androidx.lifecycle.LiveData
import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.contracts.Interactor
import com.example.vocabularyapp.di.App
import com.example.vocabularyapp.interactors.MainInteractor
import com.example.vocabularyapp.model.RepositoryImplementation
import com.example.vocabularyapp.model.local.DataSourceLocal
import com.example.vocabularyapp.model.remote.DataSourceRemote
import com.example.vocabularyapp.schedulerProvider.ISchedulerProvider
import com.example.vocabularyapp.schedulerProvider.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject
import java.lang.Appendable
import javax.inject.Inject

class MainViewModel(
    private val interactor: Interactor<AppState>
): BaseViewModel<AppState>() {

    // В этой переменной хранится последнее состояние Activity
    private var appState: AppState? = null

    fun subscribe(): LiveData<AppState> {
        return liveData
    }

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })


    override fun getData(word: String, isOnline: Boolean) {
        liveData.postValue(AppState.Loading(null))
        cancelJob()
        // Запускаем корутину для асинхронного доступа к серверу с помощью launch
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, online: Boolean) =
        withContext(Dispatchers.IO) {
            liveData.postValue(interactor.getData(word, online))
        }

     private fun handleError(error: Throwable) {
        liveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        liveData.postValue(AppState.Success(null))
        super.onCleared()
        cancelJob()
    }

    private fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }
}