package com.example.vocabularyapp.di.modules

import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.contracts.DataSource
import com.example.vocabularyapp.model.DataModel
import com.example.vocabularyapp.model.local.RoomDataBaseImplementation
import com.example.vocabularyapp.model.remote.RetrofitImplementation
import com.example.vocabularyapp.view.BaseActivity
import com.example.vocabularyapp.view.MainActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
class ApiModule {

    @Provides
    fun provideRetrofitImplementation(): DataSource<List<DataModel>> = RetrofitImplementation()
    @Provides
    fun provideRoomDataBaseImplementation(): DataSource<List<DataModel>> = RoomDataBaseImplementation()
}