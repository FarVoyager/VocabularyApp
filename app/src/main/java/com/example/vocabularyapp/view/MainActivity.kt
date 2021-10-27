package com.example.vocabularyapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.R
import com.example.vocabularyapp.contracts.View

class MainActivity : AppCompatActivity(), View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun renderData(appState: AppState) {
        TODO("Not yet implemented")
    }

    //Методичка стр. 17 из 34
}