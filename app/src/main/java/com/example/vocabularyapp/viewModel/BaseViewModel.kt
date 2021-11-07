package com.example.vocabularyapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.schedulerProvider.ISchedulerProvider
import com.example.vocabularyapp.schedulerProvider.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*

abstract class BaseViewModel<T : AppState>(
    protected val liveData: MutableLiveData<T> = MutableLiveData()
) : ViewModel() {
    // Метод, благодаря которому Activity подписывается на изменение данных,
    // возвращает LiveData, через которую и передаются данные
    abstract fun getData(word: String, isOnline: Boolean)
}