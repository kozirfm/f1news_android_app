package ru.kozirfm.f1news.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ru.kozirfm.f1news.data.model.ServerResult
import ru.kozirfm.f1news.data.repositories.ArticlesRepository
import ru.kozirfm.f1news.ui.viewstates.NewsViewState

class NewsViewModel : ViewModel() {

    val viewState = MutableLiveData<NewsViewState>()
    private val repositoryArticles = ArticlesRepository.getArticles(20)

    private val articlesObserver = Observer<ServerResult> { result ->
        result ?: return@Observer
        when (result) {
            is ServerResult.Success -> viewState.value = NewsViewState.ShowArticles(result.articles)
            is ServerResult.Error -> viewState.value = NewsViewState.ShowError(result.t)
        }
    }

    init {
        repositoryArticles.observeForever(articlesObserver)
    }

    override fun onCleared() {
        repositoryArticles.removeObserver(articlesObserver)
        super.onCleared()
    }


}