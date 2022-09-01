package com.smb.cabify.domain

import com.smb.cabify.domain.model.ProductModel
import com.smb.core.extensions.DEFAULT_FLOAT
import com.smb.core.extensions.EMPTY_STRING

private const val PRODUCT_CODE = "PRODUCT CODE"
private const val PRODUCT_NAME = "PRODUCT NAME"

internal val productDomainModelEmptyMock =
    ProductModel(
        code = EMPTY_STRING,
        name = EMPTY_STRING,
        price = DEFAULT_FLOAT
    )

internal val productDomainModelMock =
    ProductModel(
        code = PRODUCT_CODE,
        name = PRODUCT_NAME,
        price = DEFAULT_FLOAT
    )