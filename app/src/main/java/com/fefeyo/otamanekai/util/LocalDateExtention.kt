package com.fefeyo.otamanekai.util

import org.threeten.bp.LocalDate
import org.threeten.bp.temporal.WeekFields
import java.util.*

fun LocalDate.weekOfMonth(): Int {
    val week = WeekFields.of(Locale.getDefault())
    return get(week.weekOfMonth())
}
