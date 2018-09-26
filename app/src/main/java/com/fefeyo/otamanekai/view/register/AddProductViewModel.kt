package com.fefeyo.otamanekai.view.register

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.fefeyo.otamanekai.data.OtamaneDatabase
import com.fefeyo.otamanekai.data.model.ProductWork
import com.fefeyo.otamanekai.data.repository.RegisterRepository

class AddProductViewModel(application: Application) : AndroidViewModel(application) {
    val repository = RegisterRepository(OtamaneDatabase.getInstance(application))
    val productName = MutableLiveData<String>()
    val favorite = MutableLiveData<Boolean>()
    val imageByteArray = MutableLiveData<ByteArray>()
    val isSaveAvailable = Transformations.map(productName) { it.isNotEmpty() }

    fun setImage(image: ByteArray) {
        imageByteArray.postValue(image)
    }

    fun insertProductWork() {
        if(isSaveAvailable.value == true) {
            repository.insertProduct(
                    ProductWork(
                            0,
                            productName.value!!,
                            imageByteArray.value,
                            favorite.value
                    )
            )
        }
    }
}
