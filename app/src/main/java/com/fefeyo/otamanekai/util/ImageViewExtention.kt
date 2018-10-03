package com.fefeyo.otamanekai.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fefeyo.otamanekai.R

fun ImageView.load(src: String) {
    Glide.with(context)
            .load(src)
            .into(this)
}

fun ImageView.load(src: ByteArray) {
    Glide.with(context)
            .load(src)
            .into(this)
}

fun ImageView.loadForGrid(src: ByteArray?) {
    src?.let {
        Glide.with(context)
                .load(src)
//                .apply(RequestOptions.centerCropTransform())
                .into(this)
    } ?:run {
        Glide.with(context)
                .load(R.drawable.noimage)
                .into(this)
    }
}

