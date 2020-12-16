package ru.kozirfm.f1news.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import ru.kozirfm.f1news.data.entites.Article
import ru.kozirfm.f1news.data.entites.Team
import ru.kozirfm.f1news.data.entites.User
import ru.kozirfm.f1news.data.providers.RemoteDataProvider
import ru.kozirfm.f1news.data.providers.ServerDataProvider

object Repository {
    private val remoteDataProvider: RemoteDataProvider = ServerDataProvider()
    fun getArticlesPage(): LiveData<PagingData<Article>> =
        remoteDataProvider.getArticlesPage()

    suspend fun getTeams(): List<Team> = remoteDataProvider.getTeams()
    fun addUser(user: User) = remoteDataProvider.addUser(user)
}