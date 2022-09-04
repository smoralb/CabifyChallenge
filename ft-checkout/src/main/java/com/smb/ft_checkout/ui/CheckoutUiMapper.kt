package com.smb.ft_checkout.ui

import com.smb.ft_checkout.domain.model.ProductModel
import com.smb.ft_checkout.ui.adapter.CheckoutDataItems.CheckoutDataItem

interface CheckoutUiMapper {
    fun mapCheckoutItems(
        items: List<ProductModel>,
        onItemClickListener: (String) -> Unit
    ): List<CheckoutDataItem>
}

class CheckoutUiMapperImpl() : CheckoutUiMapper {

    override fun mapCheckoutItems(
        items: List<ProductModel>,
        onItemClickListener: (String) -> Unit
    ): List<CheckoutDataItem> =
        items.map { product ->
            CheckoutDataItem(
                id = product.id,
                title = product.title,
                price = product.price,
                image = product.price,
                onItemClickListener = onItemClickListener
            )
        }

}