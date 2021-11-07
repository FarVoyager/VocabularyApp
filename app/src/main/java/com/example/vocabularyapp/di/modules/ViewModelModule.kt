package com.example.vocabularyapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vocabularyapp.di.ViewModelFactory
import com.example.vocabularyapp.di.ViewModelKey
import com.example.vocabularyapp.schedulerProvider.ISchedulerProvider
import com.example.vocabularyapp.schedulerProvider.SchedulerProvider
import com.example.vocabularyapp.viewModel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

// Модуль послужит источником коллекции ViewModel’ей для фабрики:
// - мы используем этот модуль для создания ViewModel
// - мы предоставляем ключ для каждой новой ViewModel при помощи класса
// ViewModelKey, созданного выше;
// - и уже в Activity мы используем фабрику для создания нужной нам ViewModel
@Module(includes = [InteractorModule::class])
internal abstract class ViewModelModule {

     @Binds
     internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

     @Binds
     @IntoMap
     @ViewModelKey(MainViewModel::class)
     protected abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel

     @Binds
     internal abstract fun bindSchedulerProvider(schedulerProvider: SchedulerProvider): ISchedulerProvider
}