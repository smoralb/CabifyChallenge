package com.smb.ft_checkout.ui.adapter

import com.smb.core.domain.model.ItemDiscountType
import com.smb.core.presentation.adapters.BaseItem

sealed class CheckoutDataItems : BaseItem {
    data class CheckoutDataItem(
        val id: String,
        val title: String,
        val price: String,
        val priceDiscount: String,
        val image: String,
        val quantity: String,
        val hasDiscount: Boolean,
        val titleDiscount: String,
        val itemDiscountType: ItemDiscountType,
        val onOfferClickListener: (String, ItemDiscountType) -> Unit,
        val onItemClickListener: (String) -> Unit
    ) : CheckoutDataItems() {
        override fun onItemClick() {
            onItemClickListener(id)
        }
        fun onOfferClickListener() {
            onOfferClickListener(id, itemDiscountType)
        }
    }
}