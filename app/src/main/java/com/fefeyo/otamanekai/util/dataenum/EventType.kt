package com.fefeyo.otamanekai.util.dataenum

import com.fefeyo.otamanekai.R
import kotlinx.android.parcel.Parcelize

enum class EventType(val eventName: String, val colorId: Int) {
    LIVE("ライブ・ファンミーティング", R.color.live), // ライブ・ファンミーティング
    BD("BD・DVD・CD発売日", R.color.bd), // BD・DVD・CD発売日
    BROADCAST("生放送・ラジオ放送日", R.color.broadcast), // テレビ・ラジオ放送日
    OTHERS("その他", R.color.others) //　その他
}
