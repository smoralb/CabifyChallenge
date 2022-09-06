package com.smb.ft_checkout.ui

import android.content.Context
import com.smb.core.domain.dataStore.model.ProductModel
import com.smb.core.extensions.DEFAULT_FLOAT
import com.smb.core.presentation.base.BaseUiMapper
import com.smb.ft_checkout.R
import com.smb.ft_checkout.ui.adapter.CheckoutDataItems.CheckoutDataItem
import java.util.Currency
import java.util.Locale

interface CheckoutUiMapper: BaseUiMapper {
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
        if (items.isNotEmpty()) {
            items.map { product ->
                CheckoutDataItem(
                    id = product.id,
                    title = product.title,
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


    override fun mapTotalPrice(items: List<ProductModel>): String {
        var totalAmount = DEFAULT_FLOAT
        items.forEach { totalAmount += it.quantity * it.price }
        return mapAmount(totalAmount)
    }

}