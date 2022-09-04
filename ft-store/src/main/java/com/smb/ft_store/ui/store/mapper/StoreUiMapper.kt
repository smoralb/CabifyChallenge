package com.smb.ft_store.ui.store.mapper

import com.smb.ft_store.domain.model.ProductModel
import com.smb.ft_store.ui.store.adapter.StoreDataItems.StoreDataItem

interface StoreUiMapper {
    fun mapItems(model: List<ProductModel>, itemClickListener: (String) -> Unit)
            : List<StoreDataItem>
}

class StoreUiMapperImpl : StoreUiMapper {

    override fun mapItems(
        model: List<ProductModel>, itemClickListener: (String) -> Unit
    ) = model.map {
        StoreDataItem(
            id = it.code,
            name = it.name,
            price = mapProductPrice(it.price, it.currency),
            image = it.image,
            description = it.description,
            onItemClickListener = itemClickListener
        )
    }

    private fun mapProductPrice(price: Float, currency: String) =
        "$price $currency"
}