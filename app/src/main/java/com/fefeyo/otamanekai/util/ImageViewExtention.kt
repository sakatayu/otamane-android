package com.fefeyo.otamanekai.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

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

