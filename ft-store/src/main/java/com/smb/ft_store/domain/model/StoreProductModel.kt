package com.smb.ft_store.domain.model

data class StoreProductModel(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val price: Float,
    val hasDiscount: Boolean,
    val discountType: DiscountType
)

enum class DiscountType {
    DISCOUNT_2_X_1,
    DISCOUNT_BULK_PURCHASE,
    NO_DISCOUNT,
}