package com.fefeyo.otamanekai.data.repository

import androidx.lifecycle.LiveData
import com.fefeyo.otamanekai.data.OtamaneDatabase
import com.fefeyo.otamanekai.data.model.Event
import org.threeten.bp.LocalDate

class CalendarRepository(val database: OtamaneDatabase) {

    fun getEventList(): LiveData<List<Event>> = database.eventDao().findAll()

    fun getEventListFromDate(date: LocalDate): LiveData<List<Event>>
            = database.eventDao().findAllfromDate(date)
}
