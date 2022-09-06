package com.smb.core.data.dataStore.source

import androidx.datastore.core.DataStore
import com.smb.core.ShoppingCart
import com.smb.core.data.dataStore.source.mapper.LocalDataMapper
import com.smb.core.domain.dataStore.model.ProductModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface LocalSource {
    suspend fun getItems(): Flow<List<ProductModel>>
    suspend fun addNewItem(newItem: ProductModel)
}

class LocalSourceImpl(
    private val dataStore: DataStore<ShoppingCart>,
    private val mapper: LocalDataMapper
) : LocalSource {

    override suspend fun getItems(): Flow<List<ProductModel>> =
        dataStore.data.map {
            it.itemsList.map { item -> mapper.toDomainModel(item) }
        }

    override suspend fun addNewItem(newItem: ProductModel) {
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