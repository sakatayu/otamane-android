package com.fefeyo.otamanekai.view.common.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.fefeyo.otamanekai.R
import com.fefeyo.otamanekai.util.dataenum.priceFormat

class StuffView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private val nameText by lazy {
        findViewById<TextView>(R.id.name)
    }

    private val priceText by lazy {
        findViewById<TextView>(R.id.price)
    }

    var name: String = ""
        set(value) {
            field = value
            nameText.text = value
        }

    var price: Int = 0
        set(value) {
            field = value
            priceText.text = price.priceFormat()
        }

    init {
        inflate(context, R.layout.custom_stuff_view, this)
    }

}
