package com.smb.ft_store.domain.model

data class ProductModel(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val price: Float
)