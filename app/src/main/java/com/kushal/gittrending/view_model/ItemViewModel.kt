package com.kushal.gittrending.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.kushal.gittrending.model.git_trending.Item
import com.kushal.gittrending.pagination.ItemDataSource
import com.kushal.gittrending.pagination.ItemDataSourceFactory

class ItemViewModel: ViewModel() {
    var itemPagedList: LiveData<PagedList<Item>>? = null
    var liveDataSource: LiveData<PageKeyedDataSource<Int, Item>>? = null

    init {
        val itemDataSourceFactory = ItemDataSourceFactory()
        liveDataSource = itemDataSourceFactory.itemLiveDataSource

        val config = (PagedList.Config.Builder())
            .setEnablePlaceholders(false)
            .setPageSize(ItemDataSource.FIRST_PAGE)
            .build()

        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()
    }
}