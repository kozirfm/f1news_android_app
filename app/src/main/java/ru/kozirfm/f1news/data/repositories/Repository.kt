package ru.kozirfm.f1news.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import ru.kozirfm.f1news.data.entites.Article
import ru.kozirfm.f1news.data.entites.User
import ru.kozirfm.f1news.data.model.ServerResult
import ru.kozirfm.f1news.data.providers.RemoteDataProvider
import ru.kozirfm.f1news.data.providers.ServerDataProvider

object Repository {
    private val remoteDataProvider: RemoteDataProvider = ServerDataProvider()
    fun getArticlesPage(): LiveData<PagingData<Article>> =
        remoteDataProvider.getArticlesPage()
    fun getTeams(): LiveData<ServerResult> = remoteDataProvider.getTeams()
    fun addUser(user: User) = remoteDataProvider.addUser(user)
}