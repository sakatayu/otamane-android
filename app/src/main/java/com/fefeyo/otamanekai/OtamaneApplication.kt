package com.fefeyo.otamanekai

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class OtamaneApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}
