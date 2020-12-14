package ru.kozirfm.f1news.data.providers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import ru.kozirfm.f1news.data.model.Error
import ru.kozirfm.f1news.data.model.ServerResult
import ru.kozirfm.f1news.data.model.Success
import ru.kozirfm.f1news.data.retrofit.RetrofitApi

class ServerDataProvider : RemoteDataProvider {

    private val api = RetrofitApi().requestServer()

    override fun getArticlesPage(): LiveData<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            )
        ) { NewsDataSource(api) }.liveData
    }

    override fun getTeams(): LiveData<ServerResult> {
        val resultLiveData = MutableLiveData<ServerResult>()
        api.getTeams().enqueue(object : Callback<List<Team>> {
            override fun onResponse(call: Call<List<Team>>, response: Response<List<Team>>) {
                resultLiveData.value = Success(response.body())
            }

            override fun onFailure(call: Call<List<Team>>, t: Throwable) {
                resultLiveData.value = Error(t)
            }
        })
        return resultLiveData
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