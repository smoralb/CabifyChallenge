package com.smb.ft_store.data

import com.smb.core.extensions.DEFAULT_FLOAT
import com.smb.core.extensions.EMPTY_STRING
import com.smb.ft_store.data.entity.ProductEntity
import com.smb.ft_store.data.entity.ProductType
import com.smb.ft_store.data.entity.ProductsListEntity
import com.smb.ft_store.domain.model.DiscountType
import com.smb.ft_store.domain.model.StoreProductModel

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

internal val productPresentationDataModelMock =
    StoreProductModel(
        id = ProductType.MUG,
        name = EMPTY_STRING,
        price = DEFAULT_FLOAT,
        image = EMPTY_STRING,
        description = EMPTY_STRING,
        discountType = DiscountType.DISCOUNT_2_X_1,
        hasDiscount = true
    )