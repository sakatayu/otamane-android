package com.fefeyo.otamanekai.view.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.fefeyo.otamanekai.data.OtamaneDatabase
import com.fefeyo.otamanekai.data.repository.RegisterRepository

class ChooseProductViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RegisterRepository(OtamaneDatabase.getInstance(application))
    private val load = MutableLiveData<Boolean>()
    val productList = repository.getProductList()

    fun load() {
        load.value = true
    }
}
