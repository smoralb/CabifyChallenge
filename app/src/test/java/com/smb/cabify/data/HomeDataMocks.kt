package com.smb.cabify.data

import com.smb.cabify.data.entity.ProductEntity
import com.smb.cabify.data.entity.ProductsListEntity
import com.smb.cabify.domain.model.ProductModel
import com.smb.core.extensions.DEFAULT_FLOAT
import com.smb.core.extensions.EMPTY_STRING

private const val PRODUCT_CODE = "PRODUCT CODE"
private const val PRODUCT_NAME = "PRODUCT NAME"

// ENTITY

internal val productsDataListEntityMock = ProductsListEntity(
    products = listOf(
        ProductEntity(
            code = PRODUCT_CODE,
            name = PRODUCT_NAME,
            price = DEFAULT_FLOAT
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

internal val productDataModelEmptyMock =
    ProductModel(
        code = EMPTY_STRING,
        name = EMPTY_STRING,
        price = DEFAULT_FLOAT
    )

internal val productDataModelMock =
    ProductModel(
        code = PRODUCT_CODE,
        name = PRODUCT_NAME,
        price = DEFAULT_FLOAT
    )