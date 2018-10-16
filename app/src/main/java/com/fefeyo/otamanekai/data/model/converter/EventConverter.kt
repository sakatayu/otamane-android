package com.fefeyo.otamanekai.data.model.converter

import androidx.room.TypeConverter
import com.fefeyo.otamanekai.util.dataenum.*
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import java.util.*

class EventConverter {
    @TypeConverter
    fun fromHour(value: Int): Hour = Hour.values()[value]
    @TypeConverter
    fun toHour(value: Hour): Int = value.ordinal
    @TypeConverter
    fun fromMinute(value: Int): Minute = Minute.values().findLast { it.number == value }!!
    @TypeConverter
    fun toMinute(value: Minute): Int = value.number
    @TypeConverter
    fun fromTimeStamp(value: Long): LocalDate = LocalDate.ofEpochDay(value)
    @TypeConverter
    fun toTimeStamp(value: LocalDate): Long = value.toEpochDay()
    @TypeConverter
    fun fromEventType(value: Int): EventType = EventType.values()[value]
    @TypeConverter
    fun toEventType(value: EventType): Int = value.ordinal
    @TypeConverter
    fun fromRepeatSpan(value: Int): RepeatSpan = RepeatSpan.values()[value]
    @TypeConverter
    fun toRepeatSpan(value: RepeatSpan): Int = value.ordinal
    @TypeConverter
    fun fromDayOfWeek(value: Int): DayOfWeek = DayOfWeek.of(value)
    @TypeConverter
    fun toDayOfWeek(value: DayOfWeek): Int = value.value
    @TypeConverter
    fun fromTime(value: String): Time {
        val arrayValue = value.split(":")
        val hour = arrayValue[0].toInt()
        val minute = arrayValue[1].toInt()

        return Time(hour, minute)
    }
    @TypeConverter
    fun toTime(value: Time): String = "${value.hour}:${value.minute}"
}
