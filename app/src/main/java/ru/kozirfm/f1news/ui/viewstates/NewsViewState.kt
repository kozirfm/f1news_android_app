package ru.kozirfm.f1news.ui.viewstates

import ru.kozirfm.f1news.data.entites.Article

sealed class NewsViewState {
    data class ShowArticles(val articles: List<Article>?) : NewsViewState()
    data class ShowError(val t: Throwable?) : NewsViewState()
}