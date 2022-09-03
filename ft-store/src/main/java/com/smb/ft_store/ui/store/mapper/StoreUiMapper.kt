package com.smb.ft_store.ui.store.mapper

import com.smb.ft_store.domain.model.ProductModel
import com.smb.ft_store.ui.store.adapter.StoreDataItems.StoreDataItem

interface StoreUiMapper {
    fun mapItems(model: List<ProductModel>)
            : List<StoreDataItem>
}

class StoreUiMapperImpl : StoreUiMapper {

    override fun mapItems(
        model: List<ProductModel>
    ) = model.map {
        StoreDataItem(
            id = it.code,
            name = it.name,
            price = "${it.price} E",
            image = it.image,
            description = it.description
        )
    }
}