package com.smb.ft_store.ui.detail.mapper

import android.content.Context
import com.smb.core.domain.model.ItemDiscountType
import com.smb.core.domain.model.ItemDiscountType.DISCOUNT_2_X_1
import com.smb.core.domain.model.ItemDiscountType.DISCOUNT_BULK_PURCHASE
import com.smb.core.domain.model.ItemDiscountType.NO_DISCOUNT
import com.smb.core.domain.model.ProductModelRequest
import com.smb.core.extensions.DEFAULT_FLOAT
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

    val itemType: (String) -> ItemDiscountType = { id ->
        when (id) {
            "VOUCHER" -> DISCOUNT_2_X_1
            "TSHIRT" -> DISCOUNT_BULK_PURCHASE
            else -> NO_DISCOUNT
        }
    }

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
        itemDiscountType = mapOfferTitle(id),
        priceDiscount = mapPriceDiscount(itemType(id), price, quantity)
    )

    private fun itemHasDiscount(id: String) =
        when (id) {
            "VOUCHER", "TSHIRT" -> true
            else -> false
        }

    private fun mapOfferTitle(type: String) =
        when (type) {
            "VOUCHER" -> DISCOUNT_2_X_1
            "TSHIRT" -> DISCOUNT_BULK_PURCHASE
            else -> NO_DISCOUNT
        }

    private fun mapPriceDiscount(
        itemDiscountType: ItemDiscountType,
        price: Float,
        quantity: Int
    ): Float =
        when (itemDiscountType) {
            DISCOUNT_2_X_1 -> if (quantity % 2 == 0) price * quantity * 0.5f else price
            DISCOUNT_BULK_PURCHASE -> if (quantity % 3 == 0) (price * quantity) - (price * quantity * 0.05f) else price
            else -> price
        }

}