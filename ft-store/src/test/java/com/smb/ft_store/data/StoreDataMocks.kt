package com.smb.cabify.data

import com.smb.core.extensions.DEFAULT_FLOAT
import com.smb.core.extensions.EMPTY_STRING
import com.smb.ft_store.data.entity.ProductEntity
import com.smb.ft_store.data.entity.ProductType
import com.smb.ft_store.data.entity.ProductsListEntity

private const val PRODUCT_NAME = "PRODUCT NAME"
private const val PRODUCT_IMAGE = "PRODUCT IMAGE"
private const val PRODUCT_DESCRIPTION = "PRODUCT DESCRIPTION"

// ENTITY

internal val productsDataListEntityMock = ProductsListEntity(
    products = listOf(
        ProductEntity(
            code = ProductType.VOUCHER,
            name = PRODUCT_NAME,
            price = DEFAULT_FLOAT,
        )
    )
)

internal val productsDataListEntityNullMock = ProductsListEntity(
    products = listOf(
        ProductEntity(
            code = null,
            name = null,
            price = null
        )
    )
)

internal val productsDataListNullEntityMock = ProductsListEntity(products = null)

// MODEL
