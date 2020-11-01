package com.kushal.gittrending.pagination

import androidx.paging.PageKeyedDataSource
import com.kushal.gittrending.model.git_trending.GitTrendingData
import com.kushal.gittrending.model.git_trending.Item
import com.kushal.gittrending.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemDataSource : PageKeyedDataSource<Int, Item>() {

    companion object {
        const val FIRST_PAGE = 1
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Item>
    ) {
        RetrofitBuilder.getApiService()
            .getTrendingAndroidRepos(FIRST_PAGE)
            .enqueue(object : Callback<GitTrendingData> {
                override fun onResponse(
                    call: Call<GitTrendingData>,
                    response: Response<GitTrendingData>
                ) {
                    if (response.code() == 200 && response.body() != null) {
                        callback.onResult(response.body()?.items!!, null, FIRST_PAGE + 1)
                    } else {
                        callback.onError(Throwable("Network Response Code : ${response.code()}"))
                    }
                }

                override fun onFailure(call: Call<GitTrendingData>, t: Throwable) {
                    callback.onError(t)
                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        val key = if(params.key > 1) params.key -1 else null
        if (key != null) {
            RetrofitBuilder.getApiService()
                .getTrendingAndroidRepos(key)
                .enqueue(object : Callback<GitTrendingData> {
                    override fun onResponse(
                        call: Call<GitTrendingData>,
                        response: Response<GitTrendingData>
                    ) {
                        if (response.code() == 200 && response.body() != null) {
                            callback.onResult(response.body()?.items!!, key)
                        } else {
                            callback.onError(Throwable("Network Response Code : ${response.code()}"))
                        }
                    }

                    override fun onFailure(call: Call<GitTrendingData>, t: Throwable) {
                        callback.onError(t)
                    }
                })
        }
    }
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {

        RetrofitBuilder.getApiService()
            .getTrendingAndroidRepos(params.key)
            .enqueue(object : Callback<GitTrendingData> {
                override fun onResponse(
                    call: Call<GitTrendingData>,
                    response: Response<GitTrendingData>
                ) {
                    if (response.code() == 200 && response.body() != null) {
                        val total_count = response.body()?.totalCount!!
                        val key = if(total_count > 1) params.key + 1 else null
                        callback.onResult(response.body()?.items!!, key)
                    } else {
                        callback.onError(Throwable("Network Response Code : ${response.code()}"))
                    }
                }

                override fun onFailure(call: Call<GitTrendingData>, t: Throwable) {
                    callback.onError(t)
                }
            })
    }
}