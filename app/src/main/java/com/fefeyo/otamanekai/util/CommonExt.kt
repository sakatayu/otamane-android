package com.fefeyo.otamanekai.util

import android.util.Log
import com.fefeyo.otamanekai.data.OtamaneConfig

fun log(value: String?) {
    if(value != null && value.isNotEmpty()) {
        Log.d(OtamaneConfig.OTAMANE_TAG, value)
    }
}
