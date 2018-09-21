package com.fefeyo.otamanekai.view.register

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.fefeyo.otamanekai.data.api.PagingResource
import com.fefeyo.otamanekai.data.model.ProductWork
import com.fefeyo.otamanekai.data.repository.ChooseProductRepository

class ChooseProductViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ChooseProductRepository(application)
    private val load = MutableLiveData<Boolean>()

    private val pagingResource: LiveData<PagingResource<ProductWork>> =
            Transformations.switchMap(load) {
                repository.getProductList()
            }

    val productList = Transformations.switchMap(pagingResource) { it.data }
    val networkStatus = Transformations.switchMap(pagingResource) { it.networkStatus }
    val networkError = Transformations.switchMap(pagingResource) { it.error }

    fun load() {
        load.value = true
    }
}
