package com.smb.core.data.dataStore.source

import androidx.datastore.core.DataStore
import com.smb.core.data.dataStore.source.mapper.CheckoutLocalDataMapper
import com.smb.core.domain.dataStore.model.CheckoutModel
import com.smb.ft_checkout.Item
import com.smb.ft_checkout.ShoppingCart
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface CheckoutLocalSource {
    suspend fun getCheckoutItems(): Flow<List<CheckoutModel>>
    suspend fun addNewItem(newItem: CheckoutModel)
}

class CheckoutLocalSourceImpl(
    private val dataStore: DataStore<ShoppingCart>,
    private val mapper: CheckoutLocalDataMapper
) : CheckoutLocalSource {

    override suspend fun getCheckoutItems(): Flow<List<CheckoutModel>> =
        dataStore.data.map {
            it.itemsList.map { item ->
                CheckoutModel(
                    id = item.id,
                    title = item.name,
                    image = item.image,
                    price = item.price,
                    quantity = item.quantity
                )
            }
        }

    override suspend fun addNewItem(newItem: CheckoutModel) {
        val item = mapper.toDataModel(newItem)
        dataStore.updateData { shoppingCart ->
            shoppingCart.toBuilder().apply {
                if (this.itemsList.contains(item)) {
                    this.itemsList.find { it.id == newItem.id }.apply {
                        this?.toBuilder()?.quantity = newItem.quantity
                    }
                } else {
                    this.addItems(item)
                }
            }.build()
        }
    }

}