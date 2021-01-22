package ru.kozirfm.f1news.ui.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import ru.kozirfm.f1news.data.entites.Article
import ru.kozirfm.f1news.data.providers.RemoteDataProvider
import ru.kozirfm.f1news.ui.viewstates.Data
import ru.kozirfm.f1news.ui.viewstates.ViewState

class NewsViewModel(
    private val serverDataProvider: RemoteDataProvider,
    private val viewState: MutableLiveData<ViewState>,
) : ViewModel() {

    private val observer = Observer<PagingData<Article>> { articles ->
        viewState.value = Data(articles)
    }

    fun getData(): LiveData<ViewState> {
        serverDataProvider.getArticlesPage().cachedIn(viewModelScope).observeForever(observer)
        return viewState
    }

    override fun onCleared() {
        serverDataProvider.getArticlesPage().removeObserver(observer)
        super.onCleared()
    }

}