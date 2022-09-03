package com.smb.cabify.data.entity

import com.squareup.moshi.Json

data class ProductsListEntity(
    @Json(name = "products") val products: List<ProductEntity>?
)

data class ProductEntity(
    @Json(name = "code") val code: ProductType?,
    @Json(name = "name") val name: String?,
    @Json(name = "price") val price: Float?
)

enum class ProductType(val value: String) {
    VOUCHER("VOUCHER"),
    TSHIRT("TSHIRT"),
    MUG("MUG")
}