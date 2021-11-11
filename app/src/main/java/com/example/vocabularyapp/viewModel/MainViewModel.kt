package com.example.vocabularyapp.viewModel

import androidx.lifecycle.LiveData
import com.example.vocabularyapp.AppState

class MainViewModel: BaseViewModel<AppState>() {

    override fun subscribe(): LiveData<AppState> {
        return liveData
    }

    override fun getData(word: String, isOnline: Boolean) {
        //
    }
}