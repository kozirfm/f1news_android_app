package ru.kozirfm.f1news.data.retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {
    fun requestServer(): RetrofitService {

        val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        val factory = GsonConverterFactory.create(gson)

        return Retrofit.Builder()
//            .baseUrl("http://192.168.31.61:5050")
            .baseUrl("http://178.67.241.159")
            .addConverterFactory(factory)
            .build()
            .create(RetrofitService::class.java)
    }
}