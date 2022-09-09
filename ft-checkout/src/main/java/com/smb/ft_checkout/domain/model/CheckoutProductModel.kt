package com.smb.ft_checkout.domain.model

data class CheckoutProductModel(
    val id: String,
    val title: String,
    val image: String,
    val price: Float,
    val quantity: Int
)
