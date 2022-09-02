package com.smb.cabify.presentation.home.adapter

import com.smb.core.presentation.adapters.BaseItem

sealed class HomeDataItems : BaseItem {
    data class HomeDataItem(
        val code: String,
        val name: String,
        val price: Float
    ) : HomeDataItems()
}