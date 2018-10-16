package com.fefeyo.otamanekai.util.dataenum

data class Time(
        val hour: Int, // 0~23
        val minute: Int
) {
    override fun toString(): String {
        var minuteText = minute.toString()
        if(minute == 0) {
            minuteText = "00"
        }

        return "$hour:$minuteText"
    }
}
