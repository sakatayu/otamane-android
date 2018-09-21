package com.fefeyo.otamanekai.data.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.fefeyo.otamanekai.data.model.ProductWork

class ChooseProductDataSourceFactory(val context: Context) : DataSource.Factory<Int, ProductWork>() {

    val sourceLiveData = MutableLiveData<ChooseProductDataSource>()

    override fun create(): DataSource<Int, ProductWork> {
        val source = ChooseProductDataSource(context)
        sourceLiveData.postValue(source)

        return source
    }
}
