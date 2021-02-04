package ru.kozirfm.f1news.ui.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import ru.kozirfm.f1news.data.datasource.RemoteDataSource
import ru.kozirfm.f1news.data.entites.Article
import ru.kozirfm.f1news.ui.viewstates.Data
import ru.kozirfm.f1news.ui.viewstates.ViewState

class NewsViewModel(
    private val remoteDataSource: RemoteDataSource,
    private val viewState: MutableLiveData<ViewState>,
) : ViewModel() {

    private val observer = Observer<PagingData<Article>> { articles ->
        viewState.value = Data(articles)
    }

    fun getData(): LiveData<ViewState> {
        remoteDataSource.getArticlesPage().cachedIn(viewModelScope).observeForever(observer)
        return viewState
    }

    override fun onCleared() {
        remoteDataSource.getArticlesPage().removeObserver(observer)
        super.onCleared()
    }

}