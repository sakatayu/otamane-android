package com.fefeyo.otamanekai.view.register

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.fefeyo.otamanekai.R
import com.fefeyo.otamanekai.data.model.ProductWork
import com.fefeyo.otamanekai.databinding.ItemProductListBinding
import com.fefeyo.otamanekai.util.click
import com.fefeyo.otamanekai.util.loadForGrid
import com.fefeyo.otamanekai.view.common.AbstractViewHolder

class ChooseProductListAdapter :
        ListAdapter<ProductWork, ChooseProductListAdapter.ProductViewHolder>(ITEM_CALLBACK) {

    var onItemClick: (productWork: ProductWork) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
            ProductViewHolder(parent)

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ProductViewHolder(parent: ViewGroup) :
            AbstractViewHolder<ItemProductListBinding>(parent, R.layout.item_product_list) {
        fun bind(product: ProductWork) {
            binding.product = product
            binding.background.loadForGrid(product.image)
            binding.root.click {
                onItemClick(product)
            }
        }
    }

    companion object {
        private val ITEM_CALLBACK = object : DiffUtil.ItemCallback<ProductWork>() {
            override fun areItemsTheSame(oldItem: ProductWork, newItem: ProductWork): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ProductWork, newItem: ProductWork): Boolean =
                    oldItem == newItem
        }
    }
}
