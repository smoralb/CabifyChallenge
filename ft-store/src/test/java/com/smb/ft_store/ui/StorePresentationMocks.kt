package com.smb.ft_store.ui

import com.smb.core.extensions.DEFAULT_FLOAT
import com.smb.core.extensions.EMPTY_STRING
import com.smb.ft_store.data.entity.ProductType
import com.smb.ft_store.domain.model.DiscountType
import com.smb.ft_store.domain.model.StoreProductModel

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
