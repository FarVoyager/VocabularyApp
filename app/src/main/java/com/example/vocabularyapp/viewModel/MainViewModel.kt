package com.example.vocabularyapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vocabularyapp.AppState

class MainViewModel: BaseViewModel<AppState>() {
    protected val liveData: MutableLiveData<AppState> = MutableLiveData()

    override fun subscribe(): LiveData<AppState> {
        return liveData
    }

    override fun getData(word: String, isOnline: Boolean) {
        //
    }
}