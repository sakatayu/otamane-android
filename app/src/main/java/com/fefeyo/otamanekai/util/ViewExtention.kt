package com.fefeyo.otamanekai.util

import android.view.View
import androidx.core.view.isVisible
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.fefeyo.otamanekai.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun View.slideInUp(finish: () -> Unit) {
    var animation = getTag(R.integer.yoyo_slidein) as? YoYo.YoYoString
    if (animation == null || !animation.isRunning) {
        animation = YoYo.with(Techniques.SlideInUp)
                .duration(300)
                .onStart { isVisible = true }
                .onEnd { finish() }
                .playOn(this)
        setTag(R.integer.yoyo_slidein, animation)
    }
}

fun View.slideOutDown() {
    var animation = getTag(R.integer.yoyo_slideout) as? YoYo.YoYoString
    if (animation == null || !animation.isRunning) {
        animation = YoYo.with(Techniques.SlideOutDown)
                .duration(300)
                .onEnd { isVisible = false }
                .playOn(this)
        setTag(R.integer.yoyo_slideout, animation)
    }
}

fun View.click(callback: () -> Unit) {
    setOnClickListener {
        callback()
    }
}

fun View.waitClicks(callback: () -> Unit) {
    setOnClickListener {
        isEnabled = false

        Observable.timer(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { isEnabled = true }

        callback()
    }
}
