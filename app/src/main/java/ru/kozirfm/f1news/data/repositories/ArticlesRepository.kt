package ru.kozirfm.f1news.data.repositories

import androidx.lifecycle.LiveData
import ru.kozirfm.f1news.data.entites.User
import ru.kozirfm.f1news.data.model.ServerResult
import ru.kozirfm.f1news.data.providers.RemoteDataProvider
import ru.kozirfm.f1news.data.providers.ServerDataProvider

object ArticlesRepository {
    private val remoteDataProvider: RemoteDataProvider = ServerDataProvider()
    fun getArticles(count: Int): LiveData<ServerResult> = remoteDataProvider.getArticles(count)
    fun addUser(user: User) = remoteDataProvider.addUser(user)
}