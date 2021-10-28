package com.example.vocabularyapp.di

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.android.schedulers.AndroidSchedulers

class App: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<App> =
        DaggerAppComponent
            .builder()
            .withContext(applicationContext)
            .apply {
                withScheduler(AndroidSchedulers.mainThread())
            }
            .build()

}