package com.example.vocabularyapp.di

import android.app.Application
import com.example.vocabularyapp.di.koin.KoinDI
import com.example.vocabularyapp.model.local.room.Database
import com.example.vocabularyapp.schedulerProvider.SchedulerProvider
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.core.context.startKoin
import javax.inject.Inject

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(KoinDI.getInteractorModule(), KoinDI.getDatabaseModule())
        }
    }
}