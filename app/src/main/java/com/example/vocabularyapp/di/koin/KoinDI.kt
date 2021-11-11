package com.example.vocabularyapp.di.koin

import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.contracts.DataSource
import com.example.vocabularyapp.contracts.Interactor
import com.example.vocabularyapp.contracts.Repository
import com.example.vocabularyapp.di.NAME_LOCAL
import com.example.vocabularyapp.di.NAME_REMOTE
import com.example.vocabularyapp.interactors.MainInteractor
import com.example.vocabularyapp.model.DataModel
import com.example.vocabularyapp.model.RepositoryImplementation
import com.example.vocabularyapp.model.local.DataSourceLocal
import com.example.vocabularyapp.model.local.RoomDataBaseImplementation
import com.example.vocabularyapp.model.remote.DataSourceRemote
import com.example.vocabularyapp.model.remote.RetrofitImplementation
import com.example.vocabularyapp.utils.isOnline
import com.example.vocabularyapp.utils.screens.AndroidScreens
import com.example.vocabularyapp.utils.screens.IScreens
import com.example.vocabularyapp.viewModel.MainViewModel
import com.example.vocabularyapp.viewModel.WordListViewModel
import com.github.terrakok.cicerone.Cicerone
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


object KoinDI {

    private val cicerone = Cicerone.create()

    fun getScreensModule() = module {
        single { cicerone.getNavigatorHolder() }
        single { cicerone.router }
        single<IScreens> { AndroidScreens() }
    }

    fun getInteractorModule() = module {

        single(named("context")) { isOnline(androidContext()) }

        factory<DataSource<List<DataModel>>>(named(SOURCE_REMOTE)) { DataSourceRemote(RetrofitImplementation()) }
        factory<DataSource<List<DataModel>>>(named(SOURCE_LOCAL)) { DataSourceLocal(RoomDataBaseImplementation()) }

        single<Repository<List<DataModel>>>(named(NAME_REMOTE)) { RepositoryImplementation(dataSource = get(named(SOURCE_REMOTE))) }
        single<Repository<List<DataModel>>>(named(NAME_LOCAL)) { RepositoryImplementation(dataSource = get(named(SOURCE_LOCAL))) }

        single<Interactor<AppState>> { MainInteractor(remoteRepository = get(named(NAME_REMOTE)), localRepository = get(named(NAME_LOCAL))) }

        viewModel { MainViewModel() }
        viewModel { WordListViewModel(interactor = get()) }
    }
}

const val SOURCE_REMOTE = "SOURCE_REMOTE"
const val SOURCE_LOCAL = "SOURCE_LOCAL"