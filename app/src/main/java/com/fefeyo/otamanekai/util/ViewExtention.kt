package com.fefeyo.otamanekai.util

import android.animation.Animator
import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo

inline var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

fun View.slideInUp(finish: () -> Unit) {
    YoYo.with(Techniques.SlideInUp)
            .duration(300)
            .onStart { isVisible = true }
            .onEnd { finish() }
            .playOn(this)
}

fun View.slideOutDown() {
    YoYo.with(Techniques.SlideOutDown)
            .duration(300)
            .onEnd { isVisible = false }
            .playOn(this)
}

fun View.click(callback: () -> Unit) {
    setOnClickListener {
        callback()
    }
}
