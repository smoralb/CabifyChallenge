package com.smb.ft_checkout.ui

import android.content.Context
import com.smb.core.domain.dataStore.model.ProductModel
import com.smb.ft_checkout.R
import com.smb.ft_checkout.ui.adapter.CheckoutDataItems.CheckoutDataItem

interface CheckoutUiMapper {
    fun mapCheckoutItems(
        items: List<ProductModel>,
        onItemClickListener: (String) -> Unit
    ): List<CheckoutDataItem>
    fun mapTotalPrice(items: List<ProductModel>): String
}

class CheckoutUiMapperImpl(
    private val context: Context
) : CheckoutUiMapper {

    override fun mapCheckoutItems(
        items: List<ProductModel>,
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

    override fun mapTotalPrice(items: List<ProductModel>): String {
        items.filter { it.id == "" }
        return ""
    }

}