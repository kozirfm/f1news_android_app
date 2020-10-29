package ru.kozirfm.f1news.data.model

import ru.kozirfm.f1news.data.entites.Article

sealed class ServerResult {
    data class Success(val articles: List<Article>?) : ServerResult()
    data class Error(val t: Throwable?) : ServerResult()
}