package com.example.vocabularyapp.di.modules

import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.contracts.DataSource
import com.example.vocabularyapp.contracts.Interactor
import com.example.vocabularyapp.contracts.Repository
import com.example.vocabularyapp.interactors.MainInteractor
import com.example.vocabularyapp.model.DataModel
import com.example.vocabularyapp.model.RepositoryImplementation
import com.example.vocabularyapp.model.local.DataSourceLocal
import com.example.vocabularyapp.model.local.RoomDataBaseImplementation
import com.example.vocabularyapp.model.remote.DataSourceRemote
import com.example.vocabularyapp.model.remote.RetrofitImplementation
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
interface InteractorModule {

//    @Binds
//    fun bindMainInteractor(
//        mainInteractor: MainInteractor
//    ): Interactor<AppState>

//    @Binds
//    fun bindRepositoryImplementation(repositoryImplementation: RepositoryImplementation): Repository<List<DataModel>>
//
//    @Binds
//    fun bindDataSourceRemote(dataSourceRemote: DataSourceRemote): DataSource<List<DataModel>>

//    @Binds
//    fun bindDataSourceLocal(dataSourceLocal: DataSourceLocal): DataSource<List<DataModel>>



}