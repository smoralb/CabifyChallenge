package com.smb.ft_store.data.entity

data class ProductsListEntity(
    val products: List<ProductEntity>?
)

data class ProductEntity(
    val code: ProductType?,
    val name: String?,
    val price: Float?
)

enum class ProductType(val value: String) {
    VOUCHER("VOUCHER"),
    TSHIRT("TSHIRT"),
    MUG("MUG")
}