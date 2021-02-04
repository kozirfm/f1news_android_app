package ru.kozirfm.f1news.data.datasource

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import ru.kozirfm.f1news.data.entites.Article
import ru.kozirfm.f1news.data.entites.GrandPrix
import ru.kozirfm.f1news.data.entites.Team
import ru.kozirfm.f1news.data.entites.User

interface RemoteDataSource {

    fun getArticlesPage(): LiveData<PagingData<Article>>
    suspend fun getTeams(): List<Team>
    suspend fun getCalendar(): List<GrandPrix>
    fun addUser(user: User)

}