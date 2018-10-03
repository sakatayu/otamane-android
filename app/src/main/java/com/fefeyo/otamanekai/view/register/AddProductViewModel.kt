package com.fefeyo.otamanekai.view.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.fefeyo.otamanekai.data.model.ProductWork

class AddProductViewModel : ViewModel() {
    val productName = MutableLiveData<String>()
    val favorite = MutableLiveData<Boolean>()
    private val imageByteArray = MutableLiveData<ByteArray>()
    val isSaveAvailable = Transformations.map(productName) { it.isNotEmpty() }

    fun setImage(image: ByteArray) {
        imageByteArray.postValue(image)
    }

    fun generateProductWork(): ProductWork? {
        if (isSaveAvailable.value == false) return null
        return ProductWork(
                name = productName.value!!,
                image = imageByteArray.value,
                favorite = favorite.value
        )
    }
}
