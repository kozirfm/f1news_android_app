package ru.kozirfm.f1news.ui.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import ru.kozirfm.f1news.data.entites.Article
import ru.kozirfm.f1news.data.model.ArticleMapper
import ru.kozirfm.f1news.data.repositories.Repository
import ru.kozirfm.f1news.ui.viewstates.Data
import ru.kozirfm.f1news.ui.viewstates.ViewState

class NewsViewModel : ViewModel() {

    private val viewState = MutableLiveData<ViewState>()
    private val repositoryNews = Repository.getArticlesPage().cachedIn(viewModelScope)

    private val observer = Observer<PagingData<Article>> { articles ->
        val news = articles.map { ArticleMapper.mapArticlesToNews(it) }
        viewState.value = Data(news)
    }

    fun getData(): LiveData<ViewState> {
        repositoryNews.observeForever(observer)
        return viewState
    }

    override fun onCleared() {
        repositoryNews.removeObserver(observer)
        super.onCleared()
    }

}