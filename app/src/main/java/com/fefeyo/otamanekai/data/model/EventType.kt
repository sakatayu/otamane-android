package com.fefeyo.otamanekai.data.model

enum class EventType(val eventName: String) {
    LIVE("ライブ・ファンミーティング"), // ライブ・ファンミーティング
    BD("BD・DVD・CD発売日"), // BD・DVD・CD発売日
    BROADCAST("生放送・ラジオ放送日"), // テレビ・ラジオ放送日
    OTHERS("その他") //　その他
}
