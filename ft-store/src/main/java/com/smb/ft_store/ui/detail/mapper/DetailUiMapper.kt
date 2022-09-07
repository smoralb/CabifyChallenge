package com.smb.ft_store.ui.detail.mapper

import com.smb.core.domain.dataStore.model.ProductModel
import com.smb.core.presentation.base.BaseUiMapper

interface DetailUiMapper : BaseUiMapper {
    fun mapProductItem(
        id: String,
        title: String?,
        image: String?,
        price: Float,
        quantity: Int,
    ): ProductModel
}

class DetailUiMapperImpl : DetailUiMapper {

    override fun mapProductItem(
        id: String,
        title: String?,
        image: String?,
        price: Float,
        quantity: Int
    ) = ProductModel(id, title.orEmpty(), image.orEmpty(), price, quantity)
}