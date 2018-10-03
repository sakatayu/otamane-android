package com.fefeyo.otamanekai.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.fefeyo.otamanekai.data.OtamaneConfig
import com.fefeyo.otamanekai.data.OtamaneDatabase
import com.fefeyo.otamanekai.data.model.ProductWork
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class RegisterRepository(val database: OtamaneDatabase) {

    fun getProductList() : LiveData<List<ProductWork>> =
            database.productWorkDao().findAll()

    fun insertProduct(productWork: ProductWork) =
            Completable.fromAction {
                database.productWorkDao().insert(productWork)
            }
                    .subscribeOn(Schedulers.io())
                    .subscribe()



}
