package com.fefeyo.otamanekai.view.calendar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.style.LineBackgroundSpan
import androidx.core.content.ContextCompat
import com.fefeyo.otamanekai.util.dataenum.EventType
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import org.threeten.bp.LocalDate


class EventDecorator(
        private val context: Context,
        private val eventType: EventType,
        private val dates: List<LocalDate>) : DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay?): Boolean = dates.contains(day?.date)

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(CustomMultipleDotSpan())
    }

    inner class CustomMultipleDotSpan : LineBackgroundSpan {
        override fun drawBackground(canvas: Canvas?, paint: Paint?, left: Int, right: Int, top: Int, baseline: Int, bottom: Int, char: CharSequence?, start: Int, end: Int, lineNum: Int) {
            val radius = 5f
            val POSITION_LIVE = 32
            val POSITION_BD = 12
            val POSITION_BROAD_CAST = -8
            val POSITION_OTHERS = -28
            val defaultColor = paint?.color!!
            paint.color = ContextCompat.getColor(context, eventType.colorId)
            val position = when (eventType) {
                EventType.LIVE -> POSITION_LIVE
                EventType.BD -> POSITION_BD
                EventType.BROADCAST -> POSITION_BROAD_CAST
                EventType.OTHERS -> POSITION_OTHERS
            }
            canvas?.drawCircle((left + right) / 2f - position, 70f, radius, paint)
            paint.color = defaultColor
        }
    }
}

