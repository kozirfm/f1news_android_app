package ru.kozirfm.f1news.di

import androidx.lifecycle.MutableLiveData
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.kozirfm.f1news.data.providers.RemoteDataProvider
import ru.kozirfm.f1news.data.providers.DataProvider
import ru.kozirfm.f1news.data.retrofit.RetrofitApi
import ru.kozirfm.f1news.ui.viewmodels.ChampionshipViewModel
import ru.kozirfm.f1news.ui.viewmodels.NewsViewModel
import ru.kozirfm.f1news.ui.viewstates.ViewState

val appModule = module {
    single { RetrofitApi().requestServer() }
    single<RemoteDataProvider> { DataProvider(api = get()) }
    factory { MutableLiveData<ViewState>() }
    viewModel { NewsViewModel(serverDataProvider = get(), viewState = get()) }
    viewModel { ChampionshipViewModel(serverDataProvider = get(), viewState = get()) }

}