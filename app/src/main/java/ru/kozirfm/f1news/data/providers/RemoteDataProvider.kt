package ru.kozirfm.f1news.data.providers

import androidx.lifecycle.LiveData
import ru.kozirfm.f1news.data.entites.User
import ru.kozirfm.f1news.data.model.ServerResult

interface RemoteDataProvider {
    fun getArticles(count: Int): LiveData<ServerResult>
    fun getTeams(): LiveData<ServerResult>
    fun addUser(user: User)
}