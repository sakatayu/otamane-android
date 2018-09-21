package com.fefeyo.otamanekai.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.fefeyo.otamanekai.data.api.PagingResource
import com.fefeyo.otamanekai.data.model.ProductWork

class ChooseProductRepository(val context: Context) {

    fun getProductList() : LiveData<PagingResource<ProductWork>>{
        val sourceFactory = ChooseProductDataSourceFactory(context)
        val pagedListConfig = PagedList.Config.Builder()
                .setPageSize(10)
                .build()
        val livePagedList = LivePagedListBuilder(sourceFactory, pagedListConfig).build()

        return MutableLiveData<PagingResource<ProductWork>>().apply {
            postValue(
                    PagingResource(
                            data = livePagedList,
                            networkStatus =  Transformations.switchMap(sourceFactory.sourceLiveData) { it.networkStatus },
                            error = Transformations.switchMap(sourceFactory.sourceLiveData) { it.networkError }
                    )
            )
        }
    }
}
