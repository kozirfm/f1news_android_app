package ru.kozirfm.f1news.data.providers

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import ru.kozirfm.f1news.data.entites.Article
import ru.kozirfm.f1news.data.entites.Team
import ru.kozirfm.f1news.data.entites.User

interface RemoteDataProvider {
    fun getArticlesPage(): LiveData<PagingData<Article>>
    suspend fun getTeams(): List<Team>
    fun addUser(user: User)
}