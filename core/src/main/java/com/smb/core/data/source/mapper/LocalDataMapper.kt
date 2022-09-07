package com.smb.core.data.source.mapper

import com.smb.core.Item
import com.smb.core.domain.model.DiscountType
import com.smb.core.domain.model.DiscountType.DISCOUNT_2_X_1
import com.smb.core.domain.model.DiscountType.DISCOUNT_BULK_PURCHASE
import com.smb.core.domain.model.ProductModelRequest
import com.smb.core.domain.model.ProductModelResponse

interface LocalDataMapper {
    fun toDataModel(item: ProductModelRequest): Item
    fun toDomainModel(item: Item): ProductModelResponse
}

class LocalDataMapperImpl : LocalDataMapper {

    override fun toDataModel(item: ProductModelRequest): Item =
        Item.newBuilder().setId(item.id).setName(item.name).setQuantity(item.quantity)
            .setImage(item.image).setPrice(item.price).build()

    override fun toDomainModel(item: Item): ProductModelResponse =
        ProductModelResponse(
            id = item.id,
            name = item.name,
            image = item.image,
            price = item.price,
            quantity = item.quantity,
            hasDiscount = item.hasDiscount,
            discountType = mapToDiscountType(item.discountType)
        )

    private fun mapToDiscountType(type: com.smb.core.DiscountType): DiscountType =
        when (type) {
            com.smb.core.DiscountType.DISCOUNT_2_X_1 -> DISCOUNT_2_X_1
            com.smb.core.DiscountType.DISCOUNT_BULK_PURCHASE -> DISCOUNT_BULK_PURCHASE
            else -> DiscountType.NO_DISCOUNT
        }
}