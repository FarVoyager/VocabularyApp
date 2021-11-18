package com.example.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.model.AppState
import kotlinx.coroutines.*

abstract class BaseViewModel<T : AppState> : ViewModel() {
    // Метод, благодаря которому Activity подписывается на изменение данных,
    // возвращает LiveData, через которую и передаются данные
    abstract fun subscribe(): LiveData<AppState>

    abstract fun getData(word: String, isOnline: Boolean)
}