package com.smb.cabify.presentation.home.mapper

import com.smb.cabify.domain.model.ProductModel
import com.smb.cabify.presentation.home.adapter.HomeDataItems.HomeDataItem

interface HomePresentationMapper {
    fun mapItems(model: List<ProductModel>)
            : List<HomeDataItem>
}

class HomePresentationMapperImpl : HomePresentationMapper {

    override fun mapItems(
        model: List<ProductModel>
    ) = model.map {
        HomeDataItem(
            code = it.code,
            name = it.name,
            price = "${it.price} E",
            image = it.image,
            description = it.description
        )
    }
}