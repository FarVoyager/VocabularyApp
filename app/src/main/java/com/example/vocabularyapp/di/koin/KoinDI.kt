package com.example.vocabularyapp.di.koin

import androidx.room.Room
import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.contracts.DataSource
import com.example.vocabularyapp.contracts.IHistoryInteractor
import com.example.vocabularyapp.contracts.IMainInteractor
import com.example.vocabularyapp.contracts.Repository
import com.example.vocabularyapp.di.NAME_HISTORY
import com.example.vocabularyapp.di.NAME_LOCAL
import com.example.vocabularyapp.di.NAME_REMOTE
import com.example.vocabularyapp.interactors.HistoryInteractor
import com.example.vocabularyapp.interactors.MainInteractor
import com.example.vocabularyapp.model.DataModel
import com.example.vocabularyapp.model.RepositoryImplementation
import com.example.vocabularyapp.model.local.DataSourceLocal
import com.example.vocabularyapp.model.local.RoomDataBaseImplementation
import com.example.vocabularyapp.model.local.room.DataSourceHistory
import com.example.vocabularyapp.model.local.room.Database
import com.example.vocabularyapp.model.local.room.IDataSourceHistory
import com.example.vocabularyapp.model.local.room.RoomHistoryImpl
import com.example.vocabularyapp.model.remote.DataSourceRemote
import com.example.vocabularyapp.model.remote.RetrofitImplementation
import com.example.vocabularyapp.utils.isOnline
import com.example.vocabularyapp.utils.screens.AndroidScreens
import com.example.vocabularyapp.utils.screens.IScreens
import com.example.vocabularyapp.viewModel.DetailsViewModel
import com.example.vocabularyapp.viewModel.HistoryViewModel
import com.example.vocabularyapp.viewModel.WordListViewModel
import com.github.terrakok.cicerone.Cicerone
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


object KoinDI {

    fun getDatabaseModule() = module {
        single { Room.databaseBuilder(androidContext(), Database::class.java, "database").build() }
    }

    fun getInteractorModule() = module {

        single(named("context")) { isOnline(androidContext()) }

        factory<DataSource<List<DataModel>>>(named(SOURCE_REMOTE)) { DataSourceRemote(RetrofitImplementation()) }
        factory<DataSource<List<DataModel>>>(named(SOURCE_LOCAL)) { DataSourceLocal(RoomDataBaseImplementation()) }
        factory<IDataSourceHistory<List<DataModel>>>(named(SOURCE_HISTORY)) { RoomHistoryImpl(db = get()) }

        single<Repository<List<DataModel>>>(named(NAME_REMOTE)) { RepositoryImplementation(dataSource = get(named(SOURCE_REMOTE))) }
        single<Repository<List<DataModel>>>(named(NAME_LOCAL)) { RepositoryImplementation(dataSource = get(named(SOURCE_LOCAL))) }

        single<IDataSourceHistory<List<DataModel>>>(named(NAME_HISTORY)) { DataSourceHistory(historyProvider = get(named(SOURCE_HISTORY))) }

        single<IMainInteractor<AppState>>(named(INTERACTOR_MAIN)) { MainInteractor(
            remoteRepository = get(named(NAME_REMOTE)),
            localRepository = get(named(NAME_LOCAL)),
            roomHistoryRepository = get(named(NAME_HISTORY))
            )
        }

        single<IHistoryInteractor<AppState>>(named(INTERACTOR_HISTORY)) { HistoryInteractor(roomHistoryRepository = get(named(NAME_HISTORY))) }

        viewModel { WordListViewModel(mainInteractor = get(named(INTERACTOR_MAIN)), historyInteractor = get(named(INTERACTOR_HISTORY))) }
        viewModel { HistoryViewModel(interactor = get(named(INTERACTOR_HISTORY))) }
        viewModel { DetailsViewModel(historyInteractor = get(named(INTERACTOR_HISTORY))) }

    }
}

const val SOURCE_REMOTE = "SOURCE_REMOTE"
const val SOURCE_LOCAL = "SOURCE_LOCAL"
const val SOURCE_HISTORY = "SOURCE_HISTORY"

const val INTERACTOR_MAIN = "INTERACTOR_MAIN"
const val INTERACTOR_HISTORY = "INTERACTOR_HISTORY"