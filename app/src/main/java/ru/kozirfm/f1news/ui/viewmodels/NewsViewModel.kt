package ru.kozirfm.f1news.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ru.kozirfm.f1news.data.entites.Article
import ru.kozirfm.f1news.data.model.ServerResult
import ru.kozirfm.f1news.data.model.ServerResult.*
import ru.kozirfm.f1news.data.repositories.Repository
import ru.kozirfm.f1news.ui.viewstates.NewsViewState

@Suppress("UNCHECKED_CAST")
class NewsViewModel : ViewModel() {

    val viewState = MutableLiveData<NewsViewState>()
    private val repositoryArticles = Repository.getArticles(20)

    private val articlesObserver = Observer<ServerResult> { result ->
        result ?: return@Observer
        when (result) {
            is Success<*> -> viewState.value = NewsViewState.ShowArticles(result.data as List<Article>)
            is Error -> viewState.value = NewsViewState.ShowError(result.t)
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