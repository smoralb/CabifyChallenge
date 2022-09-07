package com.smb.ft_store.ui.detail.mapper

import com.smb.core.domain.model.ProductModelRequest
import com.smb.core.presentation.base.BaseUiMapper

interface DetailUiMapper : BaseUiMapper {
    fun mapProductItem(
        id: String,
        title: String?,
        image: String?,
        price: Float,
        quantity: Int,
    ): ProductModelRequest
}

class DetailUiMapperImpl : DetailUiMapper {

    override fun mapProductItem(
        id: String,
        title: String?,
        image: String?,
        price: Float,
        quantity: Int
    ) = ProductModelRequest(
        id = id,
        name = title.orEmpty(),
        image = image.orEmpty(),
        price = price,
        quantity = quantity
    )
}