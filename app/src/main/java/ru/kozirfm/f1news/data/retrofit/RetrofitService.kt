package ru.kozirfm.f1news.data.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.kozirfm.f1news.data.entites.Article
import ru.kozirfm.f1news.data.entites.Team
import ru.kozirfm.f1news.data.entites.User

interface RetrofitService {
    @GET(".")
    fun getArticles(@Query("count") count: Int): Call<List<Article>>

    @GET("championship/")
    fun getTeams(): Call<List<Team>>

    @POST("registration/")
    fun registration(@Body user: User): Call<String>
}
