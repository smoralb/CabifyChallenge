package com.smb.core.data.dataStore.source

import androidx.datastore.core.DataStore
import com.smb.core.ShoppingCart
import com.smb.core.data.dataStore.source.mapper.LocalDataMapper
import com.smb.core.domain.dataStore.model.CheckoutModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface CheckoutLocalSource {
    suspend fun getItems(): Flow<List<CheckoutModel>>
    suspend fun addNewItem(newItem: CheckoutModel)
}

class LocalSourceImpl(
    private val dataStore: DataStore<ShoppingCart>,
    private val mapper: LocalDataMapper
) : CheckoutLocalSource {

    override suspend fun getItems(): Flow<List<CheckoutModel>> =
        dataStore.data.map {
            it.itemsList.map { item -> mapper.toDomainModel(item) }
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