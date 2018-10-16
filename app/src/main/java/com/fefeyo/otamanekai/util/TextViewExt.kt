package com.fefeyo.otamanekai.util

import android.content.Context
import android.widget.TextView

fun TextView.setDrawables(
        context: Context,
        start: Int? = null,
        top: Int? = null,
        end: Int? = null,
        bottom: Int? = null
) {
    val startDrawable by lazy { start?.let { context.resources.getDrawable(start) } }
    val topDrawable by lazy { top?.let { context.resources.getDrawable(top) } }
    val endDrawable by lazy { end?.let { context.resources.getDrawable(end) } }
    val bottomDrawable by lazy { bottom?.let { context.resources.getDrawable(bottom) } }
    setCompoundDrawablesWithIntrinsicBounds(startDrawable, topDrawable, endDrawable, bottomDrawable)
}
