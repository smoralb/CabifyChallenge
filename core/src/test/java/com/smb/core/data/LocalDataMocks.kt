package com.smb.core.data

import com.smb.core.DiscountType
import com.smb.core.Item
import com.smb.core.domain.model.ItemDiscountType
import com.smb.core.domain.model.ProductModelResponse
import com.smb.core.domain.model.ProductRequest
import com.smb.core.extensions.DEFAULT_FLOAT
import com.smb.core.extensions.DEFAULT_INT
import com.smb.core.extensions.EMPTY_STRING

internal val itemMock = Item.newBuilder().setId(EMPTY_STRING).setName(EMPTY_STRING)
    .setQuantity(DEFAULT_INT).setImage(EMPTY_STRING).setPrice(DEFAULT_FLOAT).setHasDiscount(false)
    .setPriceDiscount(DEFAULT_FLOAT).setDiscountType(DiscountType.NO_DISCOUNT).build()

internal val productRequestDomainMock = ProductRequest(
    id = EMPTY_STRING,
    name = EMPTY_STRING,
    image = EMPTY_STRING,
    price = DEFAULT_FLOAT,
    quantity = DEFAULT_INT
)

internal val productModelResponseMock = ProductModelResponse(
    id = EMPTY_STRING,
    name = EMPTY_STRING,
    image = EMPTY_STRING,
    price = DEFAULT_FLOAT,
    priceAfterDiscount = DEFAULT_FLOAT,
    quantity = DEFAULT_INT,
    hasDiscount = false,
    itemDiscountType = ItemDiscountType.NO_DISCOUNT
)