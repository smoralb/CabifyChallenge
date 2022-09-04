package com.smb.ft_checkout.domain.model

data class CheckoutModel(
    val id: String,
    val title: String,
    val image: String,
    val price: Int,
    val quantity: Int
)