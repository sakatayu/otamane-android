package com.fefeyo.otamanekai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fefeyo.otamanekai.util.dataenum.*
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import java.util.*

/**
 * イベント情報
 * @param id プライマリーID
 * @param eventName イベント名
 * @param eventType イベントの種類
 * @param date 開催日・発売日・放送日
 * @param dayOfWeek 曜日(リピートの際に使う)
 * @param isAllDay 終日かどうか（trueの場合、開場・開演時間の入力が無くなる） ※CD・DVD・BD発売日等
 * @param openHour 開場時間（時）
 * @param openMinute 開場時間（分）
 * @param curtainHour 開演時間（時）
 * @param curtainMinute 開演時間（分）
 * @param repeatSpan 繰り返し間隔
 * @param productId 紐づく作品のID（外部キー）
 */
@Entity(tableName = "event")
data class Event(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val eventName: String,
        val eventType: EventType,
        val date: LocalDate,
        val dayOfWeek: DayOfWeek? = null,
        val isAllDay: Boolean = false,
        val openTime: Time? = null,
        val curtainTime: Time? = null,
        val isRepeatEvent: Boolean = false,
        val repeatSpan: RepeatSpan? = null,
        val hasSale: Boolean = false,
        val productId: Long
)
