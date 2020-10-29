package ru.kozirfm.f1news.data.providers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.kozirfm.f1news.data.entites.Article
import ru.kozirfm.f1news.data.entites.User
import ru.kozirfm.f1news.data.model.ServerResult
import ru.kozirfm.f1news.data.retrofit.RetrofitApi

class ServerDataProvider : RemoteDataProvider {

    private val api = RetrofitApi().requestServer()

    override fun getArticles(count: Int): LiveData<ServerResult> {
        val resultLiveData = MutableLiveData<ServerResult>()
        api.getArticles(count).enqueue(object : Callback<List<Article>> {
            override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {
                resultLiveData.value = ServerResult.Success(response.body())
            }

            override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                resultLiveData.value = ServerResult.Error(t)
            }

        })
        return resultLiveData
    }

    override fun addUser(user : User) {
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