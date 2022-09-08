package com.smb.core.data.source.mapper

import android.content.Context
import com.smb.core.DiscountType
import com.smb.core.Item
import com.smb.core.domain.model.ItemDiscountType
import com.smb.core.domain.model.ItemDiscountType.DISCOUNT_2_X_1
import com.smb.core.domain.model.ItemDiscountType.DISCOUNT_BULK_PURCHASE
import com.smb.core.domain.model.ProductModelRequest
import com.smb.core.domain.model.ProductModelResponse

interface LocalDataMapper {
    fun toDataModel(item: ProductModelRequest): Item
    fun toDomainModel(item: Item): ProductModelResponse
}

class LocalDataMapperImpl : LocalDataMapper {

    override fun toDataModel(item: ProductModelRequest): Item =
        Item.newBuilder().setId(item.id).setName(item.name).setQuantity(item.quantity)
            .setImage(item.image).setPrice(item.price).setHasDiscount(item.hasDiscount).
            setDiscountType(mapToDiscountType(item.itemDiscountType)).build()

    override fun toDomainModel(item: Item): ProductModelResponse =
        ProductModelResponse(
            id = item.id,
            name = item.name,
            image = item.image,
            price = item.price,
            quantity = item.quantity,
            hasDiscount = item.hasDiscount,
            itemDiscountType = mapToItemDiscountType(item.discountType)
        )

    private fun mapToItemDiscountType(type: DiscountType): ItemDiscountType =
        when (type) {
            DiscountType.DISCOUNT_2_X_1 -> DISCOUNT_2_X_1
            else -> DISCOUNT_BULK_PURCHASE
        }

    private fun mapToDiscountType(type: ItemDiscountType): DiscountType =
        when(type) {
            DISCOUNT_2_X_1 -> DiscountType.DISCOUNT_2_X_1
            else -> DiscountType.DISCOUNT_BULK_PURCHASE
        }
}