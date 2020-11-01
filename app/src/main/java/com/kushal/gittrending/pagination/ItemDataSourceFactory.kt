package com.kushal.gittrending.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.kushal.gittrending.model.git_trending.Item

class ItemDataSourceFactory: DataSource.Factory<Int, Item>() {

    val itemLiveDataSource: MutableLiveData<PageKeyedDataSource<Int, Item>> = MutableLiveData()

    override fun create(): DataSource<Int, Item> {
        val dataSource = ItemDataSource()
        itemLiveDataSource.postValue(dataSource)
        return dataSource
    }
}