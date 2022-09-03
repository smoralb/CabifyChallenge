package com.smb.ft_store.ui.store.adapter

import com.smb.core.presentation.adapters.BaseItem

sealed class StoreDataItems : BaseItem {
    data class StoreDataItem(
        override var id: String,
        val name: String,
        val price: String,
        val description: String,
        val image: String
    ) : StoreDataItems()
}