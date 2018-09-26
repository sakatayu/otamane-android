package com.fefeyo.otamanekai.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

fun Int.toByteArray(context: Context) : ByteArray {
    val bitmap = BitmapFactory.decodeResource(context.resources, this)
    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)

    return baos.toByteArray()
}

fun Bitmap.toByteArray() : ByteArray {
    val baos = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.PNG, 100, baos)

    return baos.toByteArray()
}
