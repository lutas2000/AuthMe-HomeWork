package com.lutas.authme.core.remote

import com.lutas.authme.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private val retrofit: Retrofit = createRetrofit()

    private fun createRetrofit(): Retrofit {
        val okHttpClient = createOkhttpClient()
        val gsonFactory = GsonConverterFactory.create()
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .addConverterFactory(gsonFactory)
            .build()
    }

    private fun createOkhttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    fun <T> createService(_class: Class<T>): T {
        return retrofit.create(_class)
    }
}