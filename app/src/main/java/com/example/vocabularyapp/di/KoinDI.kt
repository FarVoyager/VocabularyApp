package com.example.vocabularyapp.di

import androidx.room.Room
import com.example.core.IHistoryInteractor
import com.example.core.IMainInteractor
import com.example.model.AppState
import com.example.model.DataModel
import com.example.repository.DataSource
import com.example.repository.Repository
import com.example.repository.RepositoryImplementation
import com.example.repository.local.*
import com.example.repository.remote.DataSourceRemote
import com.example.vocabularyapp.di.NAME_HISTORY
import com.example.vocabularyapp.di.NAME_LOCAL
import com.example.vocabularyapp.di.NAME_REMOTE
import com.example.vocabularyapp.interactors.HistoryInteractor
import com.example.vocabularyapp.interactors.MainInteractor
import com.example.vocabularyapp.utils.isOnline
import com.example.vocabularyapp.view.details.DetailsFragment
import com.example.vocabularyapp.view.history.HistoryFragment
import com.example.vocabularyapp.view.wordList.WordListFragment
import com.example.vocabularyapp.viewModel.DetailsViewModel
import com.example.vocabularyapp.viewModel.HistoryViewModel
import com.example.vocabularyapp.viewModel.WordListViewModel
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

        factory<DataSource<List<DataModel>>>(named(SOURCE_REMOTE)) { DataSourceRemote() }
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

        scope<WordListFragment> {
            viewModel { WordListViewModel(mainInteractor = get(named(INTERACTOR_MAIN))) }
        }
        scope<HistoryFragment> {
            viewModel { HistoryViewModel(interactor = get(named(INTERACTOR_HISTORY))) }
        }
        scope<DetailsFragment> {
            viewModel { DetailsViewModel(historyInteractor = get(named(INTERACTOR_HISTORY))) }
        }
    }
}

const val SOURCE_REMOTE = "SOURCE_REMOTE"
const val SOURCE_LOCAL = "SOURCE_LOCAL"
const val SOURCE_HISTORY = "SOURCE_HISTORY"

const val INTERACTOR_MAIN = "INTERACTOR_MAIN"
const val INTERACTOR_HISTORY = "INTERACTOR_HISTORY"