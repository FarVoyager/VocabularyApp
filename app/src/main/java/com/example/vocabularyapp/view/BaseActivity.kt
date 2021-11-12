package com.example.vocabularyapp.view

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.R
import com.example.vocabularyapp.contracts.IMainInteractor
import com.example.vocabularyapp.viewModel.BaseViewModel
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

abstract class BaseActivity<T: AppState, I: IMainInteractor<T>> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    private val isOnline: Boolean by inject(named("context"))
    protected var isNetworkAvailable = false

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        isNetworkAvailable = isOnline
    }

    override fun onResume() {
        super.onResume()
        isNetworkAvailable = isOnline
        if (!isNetworkAvailable) {
            Toast.makeText(this, getString(R.string.NO_INT_MSG), Toast.LENGTH_SHORT).show()
        }
    }

    abstract fun renderData(appState: T)

}