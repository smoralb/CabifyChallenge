package com.smb.ft_store.ui

import com.smb.core.domain.model.ProductRequest
import com.smb.core.extensions.DEFAULT_FLOAT
import com.smb.core.extensions.DEFAULT_INT
import com.smb.core.extensions.EMPTY_STRING
import com.smb.ft_store.data.entity.ProductType
import com.smb.ft_store.domain.model.DiscountType
import com.smb.ft_store.domain.model.StoreProductModel
import com.smb.ft_store.ui.store.adapter.StoreDataItems.StoreDataItem

internal val productPresentationUiModelMock =
    StoreProductModel(
        id = ProductType.MUG,
        name = EMPTY_STRING,
        price = DEFAULT_FLOAT,
        image = EMPTY_STRING,
        description = EMPTY_STRING,
        discountType = DiscountType.DISCOUNT_2_X_1,
        hasDiscount = true
    )

internal val productStoreDataItemMock =
    StoreDataItem(
        id = ProductType.MUG,
        name = EMPTY_STRING,
        price = "$DEFAULT_FLOAT $",
        description = EMPTY_STRING,
        image = EMPTY_STRING,
        hasDiscount = true,
        onItemClickListener = {}
    )

internal val productRequestMock = ProductRequest(
    id = EMPTY_STRING,
    name = EMPTY_STRING,
    image = EMPTY_STRING,
    price = DEFAULT_FLOAT,
    quantity = DEFAULT_INT
)