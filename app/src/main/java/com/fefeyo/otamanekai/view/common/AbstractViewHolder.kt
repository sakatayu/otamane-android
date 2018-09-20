package com.fefeyo.otamanekai.view.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class AbstractViewHolder<out T: ViewDataBinding>(val binding: T):
        RecyclerView.ViewHolder(binding.root) {
    constructor(parent: ViewGroup, @LayoutRes layout: Int) : this(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), layout, parent, false)
    )

    fun bind(block: T.() -> Unit) {
        binding.block()
        binding.executePendingBindings()
    }
}
