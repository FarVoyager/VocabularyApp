package com.example.vocabularyapp.di

import android.content.Context
import android.widget.ImageView
import com.example.vocabularyapp.di.modules.ApiModule
import com.example.vocabularyapp.di.modules.InteractorModule
import com.example.vocabularyapp.di.modules.StorageModule
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import io.reactivex.Scheduler
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, StorageModule::class, AndroidInjectionModule::class, InteractorModule::class])
interface AppComponent: AndroidInjector<App> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withScheduler(scheduler: Scheduler): Builder

        fun build(): AppComponent
    }
}