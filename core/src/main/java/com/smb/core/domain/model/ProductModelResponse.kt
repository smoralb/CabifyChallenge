package com.smb.core.domain.model

data class ProductModelResponse(
    val id: String,
    val name: String,
    val image: String,
    val price: Float,
    val quantity: Int,
    val hasDiscount: Boolean,
    val discountType: DiscountType
)

enum class DiscountType {
    DISCOUNT_2_X_1,
    DISCOUNT_BULK_PURCHASE,
    NO_DISCOUNT,
}