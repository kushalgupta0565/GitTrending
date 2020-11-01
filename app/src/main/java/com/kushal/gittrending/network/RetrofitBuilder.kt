package com.kushal.gittrending.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "https://api.github.com"
    private var retrofit: Retrofit? = null

    private fun getInstance() =
        retrofit ?: Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getApiService(): ApiService {
        return getInstance().create(ApiService::class.java)
    }
}