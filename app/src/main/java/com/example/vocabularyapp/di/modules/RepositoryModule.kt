package com.example.vocabularyapp.di.modules

import com.example.vocabularyapp.contracts.DataSource
import com.example.vocabularyapp.contracts.Repository
import com.example.vocabularyapp.di.NAME_LOCAL
import com.example.vocabularyapp.di.NAME_REMOTE
import com.example.vocabularyapp.model.DataModel
import com.example.vocabularyapp.model.RepositoryImplementation
import com.example.vocabularyapp.model.local.RoomDataBaseImplementation
import com.example.vocabularyapp.model.remote.DataSourceRemote
import com.example.vocabularyapp.model.remote.RetrofitImplementation
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(@Named(NAME_REMOTE) dataSourceRemote: DataSource<List<DataModel>>
    ): Repository<List<DataModel>> = RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(@Named(NAME_LOCAL) dataSourceLocal: DataSource<List<DataModel>>
    ): Repository<List<DataModel>> = RepositoryImplementation(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): DataSource<List<DataModel>> = RetrofitImplementation()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): DataSource<List<DataModel>> = RoomDataBaseImplementation()
}