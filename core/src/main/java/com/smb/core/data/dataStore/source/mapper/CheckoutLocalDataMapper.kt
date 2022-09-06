package com.smb.core.data.dataStore.source.mapper

import com.smb.core.domain.dataStore.model.CheckoutModel
import com.smb.ft_checkout.Item

interface CheckoutLocalDataMapper {
    fun toDataModel(item: CheckoutModel): Item
}

class CheckoutLocalDataMapperImpl : CheckoutLocalDataMapper {

    override fun toDataModel(item: CheckoutModel): Item =
        Item.newBuilder().setId(item.id).setName(item.title).setQuantity(item.quantity)
            .setImage(item.image).setPrice(item.price).build()

}