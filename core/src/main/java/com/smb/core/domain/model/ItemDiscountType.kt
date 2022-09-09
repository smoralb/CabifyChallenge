package com.smb.core.domain.model

import com.smb.core.R
import com.smb.core.extensions.DEFAULT_INT

enum class ItemDiscountType(val resource: Int) {
    DISCOUNT_2_X_1(R.string.checkout_discount_title_2_x_1),
    DISCOUNT_BULK_PURCHASE(R.string.checkout_discount_title_bulk),
    NO_DISCOUNT(DEFAULT_INT)
}