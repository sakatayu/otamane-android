package com.fefeyo.otamanekai.view.calendar

import android.app.Application
import androidx.lifecycle.*
import com.fefeyo.otamanekai.data.OtamaneDatabase
import com.fefeyo.otamanekai.data.model.Event
import com.fefeyo.otamanekai.data.repository.CalendarRepository
import org.threeten.bp.LocalDate

class CalendarViewModel(application: Application) : AndroidViewModel(application) {
    val repository = CalendarRepository(OtamaneDatabase.getInstance(application))
    private val load = MutableLiveData<Boolean>()

    val allEvents: LiveData<List<Event>> = Transformations.switchMap(load) {
        repository.getEventList()
    }

    fun getEventFromDate(date: LocalDate): LiveData<List<Event>> =
            repository.database.eventDao().findAllfromDate(date)

    fun load() {
        load.postValue(true)
    }
}
