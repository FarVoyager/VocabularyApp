package com.example.vocabularyapp.di.modules

import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.view.BaseActivity
import com.example.vocabularyapp.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface StorageModule {
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

}