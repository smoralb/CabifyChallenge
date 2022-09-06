package com.smb.core.domain.dataStore.model

data class ProductModel(
    val id: String,
    val title: String,
    val image: String,
    val price: Float,
    val quantity: Int
)