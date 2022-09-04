package com.smb.ft_checkout.ui.adapter

import com.smb.core.presentation.adapters.BaseItem

sealed class CheckoutDataItems : BaseItem {
    data class CheckoutDataItem(
        val id: String,
        val title: String,
        val price: String,
        val image: String,
        val onItemClickListener: (String) -> Unit
    ) : CheckoutDataItems() {
        override fun onItemClick() {
            onItemClickListener(id)
        }
    }
}