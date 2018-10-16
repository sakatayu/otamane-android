package com.fefeyo.otamanekai.view.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.fefeyo.otamanekai.data.model.Event
import com.fefeyo.otamanekai.util.dataenum.EventType
import com.fefeyo.otamanekai.util.dataenum.RepeatSpan
import com.fefeyo.otamanekai.util.dataenum.Time
import com.fefeyo.otamanekai.util.simpleFormat
import org.threeten.bp.LocalDate

class EventRegisterDialogViewModel : ViewModel() {
    val eventName = MutableLiveData<String>()
    val date = MutableLiveData<LocalDate>().apply { postValue(LocalDate.now()) }
    val formatDate = Transformations.map(date) {
        it.simpleFormat()
    }
    val isAllDay = MutableLiveData<Boolean>().apply { postValue(false) }
    val isRepeatEvent = MutableLiveData<Boolean>().apply { postValue(false) }
    val hasSale = MutableLiveData<Boolean>().apply { postValue(false) }
    val openTime = MutableLiveData<Time>().apply { postValue(Time(12, 0))}
    val formatOpenTime = Transformations.map(openTime) { "$it" }
    val curtainTime = MutableLiveData<Time>().apply { postValue(Time(12, 0))}
    val formatCurtainTime = Transformations.map(curtainTime) { "$it" }
    val repeatSpan = MutableLiveData<RepeatSpan>().apply { postValue(RepeatSpan.WEEKLY) }
    var productId = MutableLiveData<Long>()
    var eventType = MutableLiveData<EventType>()
    val displayEventType = Transformations.map(eventType) { it.eventName }

    fun setProductId(id: Long) {
        productId.postValue(id)
    }

    fun setEventType(type: EventType) {
        eventType.postValue(type)
    }

    fun setDate(localDate: LocalDate) {
        date.postValue(localDate)
    }

    fun setOpenTime(time: Time) {
        openTime.postValue(time)
    }

    fun setCurtainTime(time: Time) {
        curtainTime.postValue(time)
    }

    fun generateEvent(): Event = Event(
            eventName = eventName.value ?: "（タイトルなし）",
            eventType = eventType.value!!,
            date = date.value!!,
            dayOfWeek = date.value?.dayOfWeek,
            isAllDay = isAllDay.value!!,
            isRepeatEvent = isRepeatEvent.value!!,
            openTime = openTime.value,
            curtainTime = curtainTime.value,
            repeatSpan = repeatSpan.value,
            productId = productId.value!!,
            hasSale = hasSale.value!!
    )
}
