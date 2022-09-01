package com.smb.cabify.presentation.main.home.adapter

import com.smb.core.presentation.adapters.BaseItem

sealed class HomeDataItems : BaseItem {
    data class HomeDataItem(
        val isbn: String,
        val title: String,
        val description: String,
        val publisher: String,
        val onItemClickListener: (String) -> Unit
    ) : HomeDataItems() {
        override fun onItemClick() {
            onItemClickListener(isbn)
        }
    }
}