package com.smb.ft_store.ui.store.mapper

import com.smb.core.presentation.base.BaseUiMapper
import com.smb.ft_store.domain.model.StoreProductModel
import com.smb.ft_store.ui.store.adapter.StoreDataItems.StoreDataItem

interface StoreUiMapper : BaseUiMapper {
    fun mapItems(model: List<StoreProductModel>, itemClickListener: (String) -> Unit)
            : List<StoreDataItem>
}

class StoreUiMapperImpl : StoreUiMapper {

    override fun mapItems(
        model: List<StoreProductModel>, itemClickListener: (String) -> Unit
    ) = model.map {
        StoreDataItem(
            id = it.id,
            name = it.name,
            price = mapAmount(it.price),
            image = it.image,
            description = it.description,
            hasDiscount = it.hasDiscount,
            onItemClickListener = itemClickListener
        )
    }
}