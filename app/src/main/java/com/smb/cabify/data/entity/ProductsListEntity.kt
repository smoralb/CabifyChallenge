package com.smb.cabify.data.entity

import com.smb.core.extensions.DEFAULT_FLOAT
import com.smb.core.extensions.EMPTY_STRING
import com.squareup.moshi.Json

data class ProductsListEntity(
    @Json(name = "products") val products: List<ProductEntity>?
)

data class ProductEntity(
    @Json(name = "code") val code: String? = EMPTY_STRING,
    @Json(name = "name") val name: String? = EMPTY_STRING,
    @Json(name = "price") val price: Float? = DEFAULT_FLOAT
)