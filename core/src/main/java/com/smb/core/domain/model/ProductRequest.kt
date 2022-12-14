package com.smb.core.domain.model

data class ProductRequest(
    val id: String,
    val name: String,
    val image: String,
    val price: Float,
    val quantity: Int,
)