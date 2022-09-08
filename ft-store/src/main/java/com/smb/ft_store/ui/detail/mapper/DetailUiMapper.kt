package com.smb.ft_store.ui.detail.mapper

import android.content.Context
import com.smb.core.domain.model.ItemDiscountType
import com.smb.core.domain.model.ProductModelRequest
import com.smb.core.presentation.base.BaseUiMapper

interface DetailUiMapper : BaseUiMapper {
    fun mapProductItem(
        id: String,
        title: String?,
        image: String?,
        price: Float,
        quantity: Int,
    ): ProductModelRequest
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
    ) = ProductModelRequest(
        id = id,
        name = title.orEmpty(),
        image = image.orEmpty(),
        price = price,
        quantity = quantity,
        hasDiscount = itemHasDiscount(id),
        itemDiscountType = mapOfferTitle(id)
    )

    private fun itemHasDiscount(id: String) =
        when (id) {
            "VOUCHER", "TSHIRT" -> true
            else -> false
        }

    private fun mapOfferTitle(type: String) =
            when(type) {
                "VOUCHER" -> ItemDiscountType.DISCOUNT_2_X_1
                else -> ItemDiscountType.DISCOUNT_BULK_PURCHASE
            }
}