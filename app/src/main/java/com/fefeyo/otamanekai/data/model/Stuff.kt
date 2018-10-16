package com.fefeyo.otamanekai.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * 関連グッズ
 * @param id プライマリーID
 * @param name 商品名
 * @param price 値段
 * @param isMust 絶対買うもの
 * @param isAdvance true: 事前物販 false: 当日物販
 * @param eventId 紐づくイベントのID(0なら該当イベントなし)
 */
@Parcelize
@Entity(tableName = "stuff")
data class Stuff(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val name: String,
        val price: Int,
        val isMust: Boolean,
        val isAdvance: Boolean,
        val eventId: Long
) : Parcelable {
        companion object {
            fun emptyStuff(eventId: Long) = Stuff(
                    0,
                    "",
                    0,
                    false,
                    false,
                    eventId
            )
        }
}
