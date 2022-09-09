package com.smb.core.data.source.mapper

import com.smb.core.DiscountType
import com.smb.core.Item
import com.smb.core.domain.model.ItemDiscountType
import com.smb.core.domain.model.ItemDiscountType.DISCOUNT_2_X_1
import com.smb.core.domain.model.ItemDiscountType.DISCOUNT_BULK_PURCHASE
import com.smb.core.domain.model.ItemDiscountType.NO_DISCOUNT
import com.smb.core.domain.model.ProductModelRequest
import com.smb.core.domain.model.ProductModelResponse

interface LocalDataMapper {
    fun toDataModel(item: ProductModelRequest): Item
    fun toDomainModel(item: Item): ProductModelResponse
}

class LocalDataMapperImpl : LocalDataMapper {

    override fun toDataModel(item: ProductModelRequest): Item =
        Item.newBuilder().setId(item.id).setName(item.name).setQuantity(item.quantity)
            .setImage(item.image).setPrice(item.price).setHasDiscount(item.hasDiscount)
            .setPriceDiscount(item.priceDiscount)
            .setDiscountType(mapToDiscountType(item.itemDiscountType)).build()

    override fun toDomainModel(item: Item): ProductModelResponse =
        ProductModelResponse(
            id = item.id,
            name = item.name,
            image = item.image,
            price = item.price,
            priceAfterDiscount = mapPriceAfterDiscount(
                item.discountType,
                item.quantity,
                item.price
            ),
            quantity = item.quantity,
            hasDiscount = getHasDiscount(item.discountType, item.quantity),
            itemDiscountType = mapToItemDiscountType(item.discountType)
        )

    private fun mapToItemDiscountType(type: DiscountType): ItemDiscountType =
        when (type) {
            DiscountType.DISCOUNT_2_X_1 -> DISCOUNT_2_X_1
            DiscountType.DISCOUNT_BULK_PURCHASE -> DISCOUNT_BULK_PURCHASE
            else -> NO_DISCOUNT
        }

    private fun mapToDiscountType(type: ItemDiscountType): DiscountType =
        when (type) {
            DISCOUNT_2_X_1 -> DiscountType.DISCOUNT_2_X_1
            DISCOUNT_BULK_PURCHASE -> DiscountType.DISCOUNT_BULK_PURCHASE
            else -> DiscountType.NO_DISCOUNT
        }

    private fun getHasDiscount(type: DiscountType, quantity: Int): Boolean =
        when (type) {
            DiscountType.DISCOUNT_2_X_1 -> quantity % 2 != 0
            DiscountType.DISCOUNT_BULK_PURCHASE -> quantity % 3 != 0
            else -> false
        }

    private fun mapPriceAfterDiscount(type: DiscountType, quantity: Int, price: Float) =
        when (type) {
            DiscountType.DISCOUNT_2_X_1 -> if (quantity % 2 == 0) price * quantity * 0.5f else price
            DiscountType.DISCOUNT_BULK_PURCHASE -> if (quantity % 3 == 0) (price * quantity) - (price * quantity * 0.05f) else price
            else -> price
        }
}