package ru.kozirfm.f1news.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.map
import ru.kozirfm.f1news.data.entites.Article
import ru.kozirfm.f1news.data.model.ArticleMapper
import ru.kozirfm.f1news.data.repositories.Repository
import ru.kozirfm.f1news.ui.viewstates.Data
import ru.kozirfm.f1news.ui.viewstates.ViewState

class NewsViewModel : ViewModel() {

    private val viewState = MutableLiveData<ViewState>()
    private val repositoryNews = Repository.getArticlesPage()

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