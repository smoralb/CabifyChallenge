package com.smb.ft_checkout.ui.adapter

sealed class CheckoutDataItems {
    data class CheckoutDataItem(
        val code: String,
        val name: String,
        val price: String,
        val image: String
    ) : CheckoutDataItems()
}