package com.smb.ft_store.ui.store.mapper

import com.smb.ft_store.domain.model.ProductModel
import com.smb.ft_store.ui.store.adapter.StoreDataItems.StoreDataItem
import java.util.Currency
import java.util.Locale

interface StoreUiMapper {
    fun mapItems(model: List<ProductModel>, itemClickListener: (String) -> Unit)
            : List<StoreDataItem>
}

class StoreUiMapperImpl : StoreUiMapper {

    private val currency = Currency.getInstance(Locale.getDefault())

    override fun mapItems(
        model: List<ProductModel>, itemClickListener: (String) -> Unit
    ) = model.map {
        StoreDataItem(
            id = it.id,
            name = it.name,
            price = mapProductPrice(it.price),
            image = it.image,
            description = it.description,
            onItemClickListener = itemClickListener
        )
    }

    private fun mapProductPrice(price: Float) =
        "$price ${currency.symbol}"
}