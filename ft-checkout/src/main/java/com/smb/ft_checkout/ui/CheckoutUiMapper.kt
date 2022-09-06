package com.smb.ft_checkout.ui

import android.content.Context
import com.smb.core.domain.dataStore.model.CheckoutModel
import com.smb.ft_checkout.R
import com.smb.ft_checkout.ui.adapter.CheckoutDataItems.CheckoutDataItem

interface CheckoutUiMapper {
    fun mapCheckoutItems(
        items: List<CheckoutModel>,
        onItemClickListener: (String) -> Unit
    ): List<CheckoutDataItem>
}

class CheckoutUiMapperImpl(
    private val context: Context
) : CheckoutUiMapper {

    override fun mapCheckoutItems(
        items: List<CheckoutModel>,
        onItemClickListener: (String) -> Unit
    ): List<CheckoutDataItem> =
        items.map { product ->
            CheckoutDataItem(
                id = product.id,
                title = product.title,
                price = product.price.toString(),
                image = product.image,
                quantity = String.format(context.getString(R.string.checkout_quantity), product.quantity),
                onItemClickListener = onItemClickListener
            )
        }

}