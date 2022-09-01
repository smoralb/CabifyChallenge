package com.smb.cabify.presentation

import com.smb.cabify.domain.model.ProductModel
import com.smb.core.extensions.DEFAULT_FLOAT
import com.smb.core.extensions.EMPTY_STRING

private const val PRODUCT_CODE = "PRODUCT CODE"
private const val PRODUCT_NAME = "PRODUCT NAME"

internal val productPresentationModelEmptyMock =
    ProductModel(
        code = EMPTY_STRING,
        name = EMPTY_STRING,
        price = DEFAULT_FLOAT
    )

internal val productPresentationModelMock =
    ProductModel(
        code = PRODUCT_CODE,
        name = PRODUCT_NAME,
        price = DEFAULT_FLOAT
    )
