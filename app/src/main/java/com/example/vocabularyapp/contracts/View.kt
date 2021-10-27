package com.example.vocabularyapp.contracts

import com.example.vocabularyapp.AppState

interface View {
    fun renderData(appState: AppState)
}