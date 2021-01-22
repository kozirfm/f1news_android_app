package ru.kozirfm.f1news.di

import androidx.lifecycle.MutableLiveData
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.kozirfm.f1news.data.providers.RemoteDataProvider
import ru.kozirfm.f1news.data.providers.DataProvider
import ru.kozirfm.f1news.data.providers.NewsDataSource
import ru.kozirfm.f1news.data.retrofit.RetrofitApi
import ru.kozirfm.f1news.ui.viewmodels.ChampionshipViewModel
import ru.kozirfm.f1news.ui.viewmodels.NewsViewModel
import ru.kozirfm.f1news.ui.viewstates.ViewState

@ExperimentalSerializationApi
val appModule = module {
    single { RetrofitApi().requestServer() }
    single { NewsDataSource(api = get()) }
    single<RemoteDataProvider> { DataProvider(api = get(), newsDataSource = get()) }
    factory { MutableLiveData<ViewState>() }
}

val viewModelsModule = module {
    viewModel { NewsViewModel(serverDataProvider = get(), viewState = get()) }
    viewModel { ChampionshipViewModel(serverDataProvider = get(), viewState = get()) }
}