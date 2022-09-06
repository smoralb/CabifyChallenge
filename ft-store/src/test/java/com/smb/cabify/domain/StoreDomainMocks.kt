package com.smb.cabify.domain

import com.smb.core.extensions.DEFAULT_FLOAT
import com.smb.core.extensions.EMPTY_STRING

private const val PRODUCT_CODE = "PRODUCT CODE"
private const val PRODUCT_NAME = "PRODUCT NAME"
private const val PRODUCT_IMAGE = "PRODUCT IMAGE"
private const val PRODUCT_DESCRIPTION = "PRODUCT DESCRIPTION"

internal val productDomainModelEmptyMock =
    ProductModel(
        code = EMPTY_STRING,
        name = EMPTY_STRING,
        price = DEFAULT_FLOAT,
        image = EMPTY_STRING,
        description = EMPTY_STRING
    )

internal val productDomainModelMock =
    ProductModel(
        code = PRODUCT_CODE,
        name = PRODUCT_NAME,
        price = DEFAULT_FLOAT,
        image = PRODUCT_IMAGE,
        description = PRODUCT_DESCRIPTION
    )