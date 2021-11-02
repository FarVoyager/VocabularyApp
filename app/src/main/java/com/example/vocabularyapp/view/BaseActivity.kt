package com.example.vocabularyapp.view

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.contracts.Interactor
import com.example.vocabularyapp.utils.isOnline
import com.example.vocabularyapp.viewModel.BaseViewModel
import javax.inject.Inject

abstract class BaseActivity<T: AppState, I: Interactor<T>> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    @Inject
    lateinit var context: Context

    protected var isNetworkAvailable = false

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        isNetworkAvailable = isOnline(context)
    }

    override fun onResume() {
        super.onResume()
        isNetworkAvailable = isOnline(applicationContext)
        if (!isNetworkAvailable) {
            Toast.makeText(this, "No Internet connection", Toast.LENGTH_SHORT).show()
        }
    }

    abstract fun renderData(appState: T)

}