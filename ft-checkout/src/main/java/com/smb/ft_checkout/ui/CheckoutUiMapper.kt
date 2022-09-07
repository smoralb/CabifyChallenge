package com.smb.ft_checkout.ui

import android.content.Context
import com.smb.core.domain.model.ProductModelResponse
import com.smb.core.extensions.DEFAULT_FLOAT
import com.smb.core.presentation.base.BaseUiMapper
import com.smb.ft_checkout.R
import com.smb.ft_checkout.ui.adapter.CheckoutDataItems.CheckoutDataItem

interface CheckoutUiMapper : BaseUiMapper {
    fun mapCheckoutItems(
        items: List<ProductModelResponse>,
        onItemClickListener: (String) -> Unit
    ): List<CheckoutDataItem>

    fun mapTotalPrice(items: List<ProductModelResponse>): String
}

class CheckoutUiMapperImpl(
    private val context: Context
) : CheckoutUiMapper {

    override fun mapCheckoutItems(
        items: List<ProductModelResponse>,
        onItemClickListener: (String) -> Unit
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
                    onItemClickListener = onItemClickListener
                )
            }
        } else emptyList()


    override fun mapTotalPrice(items: List<ProductModelResponse>): String {
        var totalAmount = DEFAULT_FLOAT
        items.forEach { totalAmount += it.quantity * it.price }
        return mapAmount(totalAmount)
    }

}