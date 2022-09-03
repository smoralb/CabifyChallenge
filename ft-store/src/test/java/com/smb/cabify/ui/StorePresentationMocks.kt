package com.smb.cabify.ui

import com.smb.core.extensions.DEFAULT_FLOAT
import com.smb.core.extensions.EMPTY_STRING
import com.smb.ft_store.domain.model.ProductModel

private const val PRODUCT_CODE = "PRODUCT CODE"
private const val PRODUCT_NAME = "PRODUCT NAME"
private const val PRODUCT_IMAGE = "PRODUCT IMAGE"
private const val PRODUCT_DESCRIPTION = "PRODUCT DESCRIPTION"

internal val productPresentationModelEmptyMock =
    ProductModel(
        code = EMPTY_STRING,
        name = EMPTY_STRING,
        price = DEFAULT_FLOAT,
        image = EMPTY_STRING,
        description = EMPTY_STRING
    )

internal val productPresentationModelMock =
    ProductModel(
        code = PRODUCT_CODE,
        name = PRODUCT_NAME,
        price = DEFAULT_FLOAT,
        image = PRODUCT_IMAGE,
        description = PRODUCT_DESCRIPTION
    )
