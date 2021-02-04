package ru.kozirfm.f1news.di

import androidx.lifecycle.MutableLiveData
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.kozirfm.f1news.data.datasource.RemoteDataSourceImplementation
import ru.kozirfm.f1news.data.datasource.NewsPagingDataSource
import ru.kozirfm.f1news.data.datasource.RemoteDataSource
import ru.kozirfm.f1news.data.retrofit.RetrofitApi
import ru.kozirfm.f1news.ui.viewmodels.ChampCalendarViewModel
import ru.kozirfm.f1news.ui.viewmodels.ChampionshipViewModel
import ru.kozirfm.f1news.ui.viewmodels.NewsViewModel
import ru.kozirfm.f1news.ui.viewstates.ViewState

@ExperimentalSerializationApi
val appModule = module {
    single { RetrofitApi().requestServer() }
    single { NewsPagingDataSource(api = get()) }
    single<RemoteDataSource> {
        RemoteDataSourceImplementation(api = get(),
            newsPagingDataSource = get())
    }
    factory { MutableLiveData<ViewState>() }
}

val viewModelsModule = module {
    viewModel { NewsViewModel(remoteDataSource = get(), viewState = get()) }
    viewModel { ChampionshipViewModel(remoteDataSource = get(), viewState = get()) }
    viewModel { ChampCalendarViewModel(remoteDataSource = get(), viewState = get()) }
}