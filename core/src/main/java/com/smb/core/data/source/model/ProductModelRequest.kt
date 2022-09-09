package com.smb.core.data.source.model

import com.smb.core.domain.model.ItemDiscountType

data class ProductModelRequest(
    val id: String,
    val name: String,
    val image: String,
    val price: Float,
    val priceDiscount: Float,
    val quantity: Int,
    val hasDiscount: Boolean,
    val itemDiscountType: ItemDiscountType
)