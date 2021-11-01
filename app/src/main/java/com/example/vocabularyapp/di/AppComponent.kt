package com.example.vocabularyapp.di

import android.app.Application
import android.content.Context
import com.example.vocabularyapp.di.modules.ActivityModule
import com.example.vocabularyapp.di.modules.InteractorModule
import com.example.vocabularyapp.di.modules.RepostioryModule
import com.example.vocabularyapp.di.modules.ViewModelModule
import com.example.vocabularyapp.schedulerProvider.SchedulerProvider
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.Scheduler
import javax.inject.Singleton

@Singleton
@Component(modules = [ActivityModule::class, InteractorModule::class, RepostioryModule::class, ViewModelModule::class, AndroidInjectionModule::class])
interface AppComponent: AndroidInjector<App> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withApplication(application: Application): Builder

        @BindsInstance
        fun withScheduler(scheduler: SchedulerProvider): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)
}