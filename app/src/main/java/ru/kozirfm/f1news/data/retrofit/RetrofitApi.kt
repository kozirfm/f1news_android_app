package ru.kozirfm.f1news.data.retrofit

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {
    fun requestServer(): RetrofitService {

        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient()
            .newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        val factory = GsonConverterFactory.create(gson)

        return Retrofit.Builder()
            .baseUrl("http://178.67.241.159")
            .client(client)
            .addConverterFactory(factory)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(RetrofitService::class.java)
    }
}