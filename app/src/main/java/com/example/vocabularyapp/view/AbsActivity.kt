package com.example.vocabularyapp.view

import android.os.Bundle
import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.contracts.Presenter
import com.example.vocabularyapp.contracts.View
import com.example.vocabularyapp.presenters.MainPresenterImpl
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.Scheduler
import javax.inject.Inject

open class AbsActivity: BaseActivity<AppState>(), HasAndroidInjector {

    @Inject
    lateinit var scheduler: Scheduler

    override fun createPresenter(): Presenter<AppState, View> {
        return MainPresenterImpl(scheduler)
    }

    override fun renderData(appState: AppState) {
        //
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}