package com.smb.ft_store.ui.detail.mapper

import android.content.Context
import com.smb.core.domain.model.ProductRequest
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.presentation.base.BaseUiMapper
import com.smb.ft_store.R
import com.smb.ft_store.domain.model.DiscountType
import com.smb.ft_store.domain.model.DiscountType.DISCOUNT_2_X_1
import com.smb.ft_store.domain.model.DiscountType.DISCOUNT_BULK_PURCHASE

interface DetailUiMapper : BaseUiMapper {
    fun mapProductItem(
        id: String,
        title: String?,
        image: String?,
        price: Float,
        quantity: Int,
    ): ProductRequest

    fun mapDiscountType(type: DiscountType): String
}

class DetailUiMapperImpl(
    private val context: Context
) : DetailUiMapper {

    override fun mapProductItem(
        id: String,
        title: String?,
        image: String?,
        price: Float,
        quantity: Int
    ) = ProductRequest(
        id = id,
        name = title.orEmpty(),
        image = image.orEmpty(),
        price = price,
        quantity = quantity
    )

    override fun mapDiscountType(type: DiscountType): String =
        when (type) {
            DISCOUNT_2_X_1 -> context.getString(R.string.details_view_discount_2_x_1)
            DISCOUNT_BULK_PURCHASE -> context.getString(R.string.details_view_discount_bulk)
            else -> EMPTY_STRING
        }
}