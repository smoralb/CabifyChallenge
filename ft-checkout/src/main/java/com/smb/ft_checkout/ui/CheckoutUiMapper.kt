package com.smb.ft_checkout.ui

import android.content.Context
import com.smb.core.domain.model.ItemDiscountType
import com.smb.core.domain.model.ItemDiscountType.DISCOUNT_2_X_1
import com.smb.core.domain.model.ItemDiscountType.DISCOUNT_BULK_PURCHASE
import com.smb.core.domain.model.ProductModelResponse
import com.smb.core.extensions.DEFAULT_FLOAT
import com.smb.core.presentation.base.BaseUiMapper
import com.smb.ft_checkout.R
import com.smb.ft_checkout.ui.adapter.CheckoutDataItems.CheckoutDataItem

interface CheckoutUiMapper : BaseUiMapper {
    fun mapCheckoutItems(
        items: List<ProductModelResponse>,
        onItemClickListener: (String) -> Unit,
        onOfferClickListener: (String, ItemDiscountType) -> Unit
    ): List<CheckoutDataItem>

    fun mapTotalPrice(items: List<ProductModelResponse>): String
}

class CheckoutUiMapperImpl(
    private val context: Context
) : CheckoutUiMapper {

    override fun mapCheckoutItems(
        items: List<ProductModelResponse>,
        onItemClickListener: (String) -> Unit,
        onOfferClickListener: (String, ItemDiscountType) -> Unit
    ): List<CheckoutDataItem> =
        if (items.isNotEmpty()) {
            items.map { product ->
                CheckoutDataItem(
                    id = product.id,
                    title = product.name,
                    price = product.price.toString(),
                    image = product.image,
                    quantity = String.format(
                        context.getString(R.string.checkout_quantity),
                        product.quantity
                    ),
                    onItemClickListener = onItemClickListener,
                    onOfferClickListener = onOfferClickListener,
                    hasDiscount = product.hasDiscount,
                    titleDiscount = mapOfferTitle(product.itemDiscountType),
                    itemDiscountType = product.itemDiscountType
                )
            }
        } else emptyList()


    override fun mapTotalPrice(items: List<ProductModelResponse>): String {
        var totalAmount = DEFAULT_FLOAT
        items.forEach { totalAmount += it.quantity * it.price }
        return mapAmount(totalAmount)
    }

    private fun mapOfferTitle(type: ItemDiscountType) =
        context.getString(
            when(type) {
                DISCOUNT_2_X_1 -> R.string.checkout_discount_title_2_x_1
                DISCOUNT_BULK_PURCHASE -> R.string.checkout_discount_title_bulk
            }
        )

}