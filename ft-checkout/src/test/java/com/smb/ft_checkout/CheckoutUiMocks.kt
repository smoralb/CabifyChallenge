package com.smb.ft_checkout

import android.view.View
import com.smb.core.domain.model.ItemDiscountType
import com.smb.core.domain.model.ProductModelResponse
import com.smb.core.extensions.DEFAULT_FLOAT
import com.smb.core.extensions.DEFAULT_INT
import com.smb.core.extensions.EMPTY_STRING
import com.smb.ft_checkout.ui.adapter.CheckoutDataItems.CheckoutDataItem

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


private val onItemClickListener: (String) -> Unit = {}

private val onOfferClickListener: (String, ItemDiscountType) -> Unit = { _, _ -> }

internal val checkoutDataItemMock = CheckoutDataItem(
    id = EMPTY_STRING,
    title = EMPTY_STRING,
    price = "$DEFAULT_FLOAT $",
    priceDiscount = "$DEFAULT_FLOAT $",
    image = EMPTY_STRING,
    quantity = EMPTY_STRING,
    hasDiscount = false,
    showPriceDiscount = View.GONE,
    titleDiscount = EMPTY_STRING,
    itemDiscountType = ItemDiscountType.NO_DISCOUNT,
    onOfferClickListener = onOfferClickListener,
    onItemClickListener = onItemClickListener
)