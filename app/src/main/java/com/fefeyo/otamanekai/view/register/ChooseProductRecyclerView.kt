package com.fefeyo.otamanekai.view.register

import android.content.Context
import android.util.AttributeSet
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.fefeyo.otamanekai.data.model.ProductWork

class ChooseProductRecyclerView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    private val adapter = ChooseProductListAdapter()
    var onItemClick: (productWork: ProductWork) -> Unit = {}
        set(value) {
            field = value
            adapter.onItemClick = value
        }

    var products: List<ProductWork>? = null
        set(value) = adapter.submitList(value)

    init {
        setAdapter(adapter)
    }
}
