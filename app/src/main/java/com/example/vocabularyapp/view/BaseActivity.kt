package com.example.vocabularyapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.contracts.Presenter
import com.example.vocabularyapp.contracts.View
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<T: AppState> : AppCompatActivity(), View {

    protected lateinit var presenter: Presenter<T, View>

    protected abstract fun  createPresenter(): Presenter<T, View>

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

//     Когда View готова отображать данные, передаём ссылку на View в презентер
    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    // При пересоздании или уничтожении View удаляем ссылку, иначе в презентере
    // будет ссылка на несуществующую View
    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}