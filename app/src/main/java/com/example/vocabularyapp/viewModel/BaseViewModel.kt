package com.example.vocabularyapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.schedulerProvider.ISchedulerProvider
import com.example.vocabularyapp.schedulerProvider.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T: AppState>(
    protected val liveData: MutableLiveData<T> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ViewModel() {
    // Метод, благодаря которому Activity подписывается на изменение данных,
    // возвращает LiveData, через которую и передаются данные
    open fun getData(word: String, isOnline: Boolean): LiveData<T> = liveData

    override fun onCleared() {
        compositeDisposable.clear()
    }
}