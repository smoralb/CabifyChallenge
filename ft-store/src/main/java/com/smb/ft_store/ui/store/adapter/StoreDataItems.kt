package com.smb.ft_store.ui.store.adapter

import com.smb.core.presentation.adapters.BaseItem
import com.smb.ft_store.data.entity.ProductType

sealed class StoreDataItems : BaseItem {
    data class StoreDataItem(
        val id: ProductType?,
        val name: String,
        val price: String,
        val description: String,
        val image: String,
        val hasDiscount: Boolean,
        val onItemClickListener: (String) -> Unit
    ) : StoreDataItems() {
        override fun onItemClick() {
            onItemClickListener(id?.value.orEmpty())
        }
    }
}