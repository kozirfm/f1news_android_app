package ru.kozirfm.f1news.ui.viewstates

import ru.kozirfm.f1news.data.entites.Article

sealed class MainViewState {
    data class ShowArticles(val articles: List<Article>?) : MainViewState()
    data class ShowError(val t: Throwable?) : MainViewState()
}