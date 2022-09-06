package com.smb.core.data.dataStore.source.mapper

import com.smb.core.Item
import com.smb.core.domain.dataStore.model.CheckoutModel

interface LocalDataMapper {
    fun toDataModel(item: CheckoutModel): Item
    fun toDomainModel(item: Item): CheckoutModel
}

class LocalDataMapperImpl : LocalDataMapper {

    override fun toDataModel(item: CheckoutModel): Item =
        Item.newBuilder().setId(item.id).setName(item.title).setQuantity(item.quantity)
            .setImage(item.image).setPrice(item.price).build()

    override fun toDomainModel(item: Item): CheckoutModel =
        CheckoutModel(
            id = item.id,
            title = item.name,
            image = item.image,
            price = item.price,
            quantity = item.quantity
        )

}