package ru.kozirfm.f1news.data.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class MainInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer bearer")
            .build()
        return chain.proceed(request)
    }
}