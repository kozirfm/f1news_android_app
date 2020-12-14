package ru.kozirfm.f1news.data.providers

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import ru.kozirfm.f1news.data.entites.Article
import ru.kozirfm.f1news.data.entites.User
import ru.kozirfm.f1news.data.model.ServerResult

interface RemoteDataProvider {
    fun getArticlesPage(): LiveData<PagingData<Article>>
    fun getTeams(): LiveData<ServerResult>
    fun addUser(user: User)
}