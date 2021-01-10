package ru.kozirfm.f1news.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.map
import ru.kozirfm.f1news.data.entites.Article
import ru.kozirfm.f1news.data.model.ArticleMapper
import ru.kozirfm.f1news.data.providers.RemoteDataProvider
import ru.kozirfm.f1news.ui.viewstates.Data
import ru.kozirfm.f1news.ui.viewstates.ViewState

class NewsViewModel(
    private val serverDataProvider: RemoteDataProvider,
    private val viewState: MutableLiveData<ViewState>,
) : ViewModel() {

    private val observer = Observer<PagingData<Article>> { articles ->
        val news = articles.map { ArticleMapper.mapArticlesToNews(it) }
        viewState.value = Data(news)
    }

    fun getData(): LiveData<ViewState> {
        serverDataProvider.getArticlesPage().observeForever(observer)
        return viewState
    }

    override fun onCleared() {
        serverDataProvider.getArticlesPage().removeObserver(observer)
        super.onCleared()
    }

}