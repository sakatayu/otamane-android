package com.fefeyo.otamanekai.data.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
import com.fefeyo.otamanekai.R
import com.fefeyo.otamanekai.data.api.NetworkStatus
import com.fefeyo.otamanekai.data.model.ProductWork
import com.fefeyo.otamanekai.util.toByteArray

class ChooseProductDataSource(val context: Context) : PositionalDataSource<ProductWork>(){

    val networkStatus = MutableLiveData<NetworkStatus>()
    val networkError = MutableLiveData<Throwable>()

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<ProductWork>) {
        networkStatus.postValue(NetworkStatus.LOADING)

        callback.onResult(dummyProductList())

        networkStatus.postValue(NetworkStatus.LOADING)
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<ProductWork>) {
        networkStatus.postValue(NetworkStatus.LOADING)

        val dummyData = dummyProductList()
        callback.onResult(dummyData, params.requestedStartPosition, params.pageSize)

        networkStatus.postValue(NetworkStatus.LOADING)
    }

    private fun dummyProductList() : List<ProductWork> =
            (1..10).map { ProductWork(1, "ラブライブ！サンシャイン！！", R.drawable.sample.toByteArray(context)) }


}
