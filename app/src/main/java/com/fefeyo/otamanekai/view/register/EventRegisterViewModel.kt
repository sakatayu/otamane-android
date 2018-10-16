package com.fefeyo.otamanekai.view.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.fefeyo.otamanekai.data.OtamaneDatabase
import com.fefeyo.otamanekai.data.model.Event
import com.fefeyo.otamanekai.data.repository.RegisterRepository

class EventRegisterViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RegisterRepository(OtamaneDatabase.getInstance(application))
    private val load = MutableLiveData<Long>()
    val eventList: LiveData<List<Event>> = Transformations.switchMap(load) {
        repository.getEventList(it)
    }
    val hasData = Transformations.map(eventList) {
        it.isNotEmpty()
    }

    fun load(productId: Long) {
        load.postValue(productId)
    }

    fun insertEvent(event: Event) = repository.insertEvent(event)

}
