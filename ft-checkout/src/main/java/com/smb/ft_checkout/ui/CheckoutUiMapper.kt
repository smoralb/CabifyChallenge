package com.smb.ft_checkout.ui

import android.content.Context
import android.view.View
import com.smb.core.domain.model.ItemDiscountType
import com.smb.core.domain.model.ItemDiscountType.NO_DISCOUNT
import com.smb.core.domain.model.ProductModelResponse
import com.smb.core.extensions.DEFAULT_FLOAT
import com.smb.core.extensions.DEFAULT_INT
import com.smb.core.extensions.EMPTY_STRING
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
                    price = mapAmount(product.price * product.quantity),
                    image = product.image,
                    quantity = String.format(
                        context.getString(R.string.checkout_quantity),
                        product.quantity
                    ),
                    priceDiscount = mapAmount(product.priceAfterDiscount),
                    onItemClickListener = onItemClickListener,
                    onOfferClickListener = onOfferClickListener,
                    hasDiscount = product.hasDiscount,
                    titleDiscount = mapTitleDiscount(product.itemDiscountType.resource),
                    itemDiscountType = product.itemDiscountType,
                    showPriceDiscount = showPriceDiscount(product.itemDiscountType)
                )
            }
        } else emptyList()


    override fun mapTotalPrice(items: List<ProductModelResponse>): String {
        var totalAmount = DEFAULT_FLOAT
        items.forEach { totalAmount += it.priceAfterDiscount }
        return mapAmount(totalAmount)
    }

    private fun mapTitleDiscount(resource: Int): String =
        if (resource != DEFAULT_INT) context.getString(resource)
        else EMPTY_STRING


    private fun showPriceDiscount(type: ItemDiscountType): Int =
        when (type) {
            NO_DISCOUNT -> View.GONE
            else -> View.VISIBLE
        }

}