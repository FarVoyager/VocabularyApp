package com.example.vocabularyapp.di

import android.app.Application
import com.example.vocabularyapp.schedulerProvider.SchedulerProvider
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class App: Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
            .builder()
            .withApplication(this)
            .withContext(applicationContext)
            .apply {
                withScheduler(SchedulerProvider())
            }
            .build()
            .inject(this)
    }


}