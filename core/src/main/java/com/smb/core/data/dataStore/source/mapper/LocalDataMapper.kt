package com.smb.core.data.dataStore.source.mapper

import com.smb.core.Item
import com.smb.core.domain.dataStore.model.ProductModel

interface LocalDataMapper {
    fun toDataModel(item: ProductModel): Item
    fun toDomainModel(item: Item): ProductModel
}

class LocalDataMapperImpl : LocalDataMapper {

    override fun toDataModel(item: ProductModel): Item =
        Item.newBuilder().setId(item.id).setName(item.title).setQuantity(item.quantity)
            .setImage(item.image).setPrice(item.price).build()

    override fun toDomainModel(item: Item): ProductModel =
        ProductModel(
            id = item.id,
            title = item.name,
            image = item.image,
            price = item.price,
            quantity = item.quantity
        )

}