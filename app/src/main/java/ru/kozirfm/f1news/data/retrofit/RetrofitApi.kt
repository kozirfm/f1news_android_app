package ru.kozirfm.f1news.data.retrofit

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

class RetrofitApi {
    fun requestServer(): RetrofitService {

        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(MainInterceptor())
            .addNetworkInterceptor(httpLoggingInterceptor)
            .build()

        val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        val factory = GsonConverterFactory.create(gson)

        return Retrofit.Builder()
            .baseUrl("http://78.37.150.225")
            .client(okHttpClient)
            .addConverterFactory(factory)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create()
    }
}