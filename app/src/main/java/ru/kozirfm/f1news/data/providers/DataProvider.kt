package ru.kozirfm.f1news.data.providers

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.kozirfm.f1news.data.entites.Article
import ru.kozirfm.f1news.data.entites.Team
import ru.kozirfm.f1news.data.entites.User
import ru.kozirfm.f1news.data.retrofit.RetrofitService

class DataProvider(private val api: RetrofitService, private val newsDataSource: NewsDataSource) :
    RemoteDataProvider {

    override fun getArticlesPage(): LiveData<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            )
        ) { newsDataSource }.liveData
    }

    override suspend fun getTeams(): List<Team> {
        return api.getTeamsAsync().await()
    }

    override fun addUser(user: User) {
        api.registration(user).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                println(response.body())
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                //error
            }
        })
    }


}