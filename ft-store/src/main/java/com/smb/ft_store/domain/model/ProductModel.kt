package com.smb.ft_store.domain.model

data class ProductModel(
    val code: String,
    val name: String,
    val description: String,
    val price: Float,
    val image: String,
    val currency: String
)