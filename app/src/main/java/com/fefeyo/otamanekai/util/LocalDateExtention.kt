package com.fefeyo.otamanekai.util

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.WeekFields
import java.util.*

fun LocalDate.weekOfMonth(): Int {
    val week = WeekFields.of(Locale.getDefault())
    return get(week.weekOfMonth())
}

fun LocalDate.simpleFormat(): String {
    return format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"))
}
