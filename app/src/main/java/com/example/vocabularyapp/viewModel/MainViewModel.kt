package com.example.vocabularyapp.viewModel

import androidx.lifecycle.LiveData
import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.contracts.Interactor
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
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject
import java.lang.Appendable
import javax.inject.Inject

class MainViewModel(
    private val interactor: Interactor<AppState>,
    private val scheduler: ISchedulerProvider
): BaseViewModel<AppState>() {

    // В этой переменной хранится последнее состояние Activity
    private var appState: AppState? = null

    fun subscribe(): LiveData<AppState> {
        return liveData
    }

    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler.ui())
                .doOnSubscribe { liveData.postValue(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
        return super.getData(word, isOnline)
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return  object : DisposableObserver<AppState>() {
            // Данные успешно загружены; сохраняем их и передаем во View (через
            // LiveData). View сама разберётся, как их отображать
            override fun onNext(t: AppState) {
                appState = t
                liveData.postValue(t)
            }
            // В случае ошибки передаём её в Activity таким же образом через LiveData
            override fun onError(e: Throwable) {
                liveData.postValue(AppState.Error(e))
            }

            override fun onComplete() {
                //do nothing
            }
        }
    }
}