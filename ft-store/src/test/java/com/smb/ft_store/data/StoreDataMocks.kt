package com.smb.ft_store.data

import com.smb.core.extensions.DEFAULT_FLOAT
import com.smb.core.extensions.EMPTY_STRING
import com.smb.ft_store.data.entity.ProductEntity
import com.smb.ft_store.data.entity.ProductType
import com.smb.ft_store.data.entity.ProductsListEntity
import com.smb.ft_store.domain.model.DiscountType
import com.smb.ft_store.domain.model.StoreProductModel

private const val VOUCHER_IMAGE_URL =
    "https://images.unsplash.com/photo-1494426383302-7b9d36a1a028?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1738&q=80"
private const val T_SHIRT_IMAGE_URL =
    "https://images.unsplash.com/photo-1529374255404-311a2a4f1fd9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1738&q=80"
private const val MUG_IMAGE_URL =
    "https://images.unsplash.com/photo-1516390118834-21602d501886?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1836&q=80"
private const val FAKE_DESCRIPTION =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"


// ENTITY

internal val productsDataListEntityMock = ProductsListEntity(
    products = listOf(
        ProductEntity(
            code = ProductType.MUG,
            name = EMPTY_STRING,
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

internal val productsDataEntityMock = ProductEntity(
    code = ProductType.MUG,
    name = EMPTY_STRING,
    price = DEFAULT_FLOAT,
)

// MODEL

internal val productDataModelMugMock =
    StoreProductModel(
        id = ProductType.MUG,
        name = EMPTY_STRING,
        price = DEFAULT_FLOAT,
        image = MUG_IMAGE_URL,
        description = FAKE_DESCRIPTION,
        discountType = DiscountType.NO_DISCOUNT,
        hasDiscount = false
    )

internal val productDataModelVoucherMock =
    StoreProductModel(
        id = ProductType.VOUCHER,
        name = EMPTY_STRING,
        price = DEFAULT_FLOAT,
        image = VOUCHER_IMAGE_URL,
        description = FAKE_DESCRIPTION,
        discountType = DiscountType.DISCOUNT_2_X_1,
        hasDiscount = true
    )

internal val productDataModelTshirtMock =
    StoreProductModel(
        id = ProductType.TSHIRT,
        name = EMPTY_STRING,
        price = DEFAULT_FLOAT,
        image = T_SHIRT_IMAGE_URL,
        description = FAKE_DESCRIPTION,
        discountType = DiscountType.DISCOUNT_BULK_PURCHASE,
        hasDiscount = true
    )