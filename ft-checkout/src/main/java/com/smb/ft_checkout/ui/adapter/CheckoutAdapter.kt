package com.smb.ft_checkout.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.smb.core.presentation.adapters.BaseAdapter
import com.smb.ft_checkout.BR
import com.smb.ft_checkout.R
import com.smb.ft_checkout.ui.adapter.CheckoutDataItems.CheckoutDataItem

private const val ITEM_STORE = 0

class CheckoutAdapter : BaseAdapter<CheckoutDataItem>() {

    override fun updateData(newItems: List<CheckoutDataItem>) {
        items = newItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ITEM_STORE -> createItemViewHolder(parent)
            else -> throw IllegalArgumentException()
        }

    override fun getItemViewType(position: Int): Int =
        when (items[position]) {
            is CheckoutDataItem -> ITEM_STORE
            else -> throw IllegalArgumentException()
        }

    private fun createItemViewHolder(parent: ViewGroup) =
        CheckoutViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_checkout,
                parent,
                false
            )
        )

    inner class CheckoutViewHolder(
        binding: ViewDataBinding
    ) : BaseViewHolder<CheckoutDataItem>(BR.item, binding)
}

