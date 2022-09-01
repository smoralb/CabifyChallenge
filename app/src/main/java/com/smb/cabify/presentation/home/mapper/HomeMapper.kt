package com.smb.cabify.presentation.home.mapper

import com.smb.cabify.domain.model.ProductModel
import com.smb.cabify.presentation.home.adapter.HomeDataItems

interface HomePresentationMapper {
    fun mapItems(model: List<ProductModel>, itemClickListener: (String) -> Unit)
            : List<HomeDataItems.HomeDataItem>
}

class HomePresentationMapperImpl : HomePresentationMapper {

    override fun mapItems(
        model: List<ProductModel>,
        itemClickListener: (String) -> Unit
    ) = model.map {
        HomeDataItems.HomeDataItem(
            code = it.code,
            name = it.name,
            price = it.price,
            onItemClickListener = itemClickListener
        )
    }
}