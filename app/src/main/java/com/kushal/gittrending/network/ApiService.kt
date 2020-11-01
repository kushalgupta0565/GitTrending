package com.kushal.gittrending.network

import com.kushal.gittrending.model.git_trending.GitTrendingData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/search/repositories?q=android&sort=stars&order=desc&per_page=10")
    fun getTrendingAndroidRepos(@Query("page") pageToFetch: Int): Call<GitTrendingData>


}