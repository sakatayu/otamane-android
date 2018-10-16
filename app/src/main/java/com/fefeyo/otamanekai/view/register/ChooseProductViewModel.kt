package com.fefeyo.otamanekai.view.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fefeyo.otamanekai.data.OtamaneDatabase
import com.fefeyo.otamanekai.data.model.ProductWork
import com.fefeyo.otamanekai.data.repository.RegisterRepository

class ChooseProductViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RegisterRepository(OtamaneDatabase.getInstance(application))
    val productList: LiveData<List<ProductWork>> = repository.getProductList()

    fun insertProductWork(productWork: ProductWork) = repository.insertProduct(productWork)

}
