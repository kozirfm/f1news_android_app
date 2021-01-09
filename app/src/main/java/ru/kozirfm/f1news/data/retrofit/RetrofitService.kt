package ru.kozirfm.f1news.data.retrofit

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.*
import ru.kozirfm.f1news.data.entites.Article
import ru.kozirfm.f1news.data.entites.Team
import ru.kozirfm.f1news.data.entites.User

interface RetrofitService {
    @GET(".")
    fun getArticles(@Query("count") count: Int): Call<List<Article>>

    @GET(".")
    suspend fun getArticlesPage(@Query("page") page: Int): List<Article>

    @GET("championship/")
    fun getTeamsAsync(): Deferred<List<Team>>

    @POST("registration/")
    fun registration(@Body user: User): Call<String>
}
