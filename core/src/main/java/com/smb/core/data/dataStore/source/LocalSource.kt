package com.smb.core.data.dataStore.source

import androidx.datastore.core.DataStore
import com.smb.core.ShoppingCart
import com.smb.core.data.dataStore.source.mapper.LocalDataMapper
import com.smb.core.domain.dataStore.model.ProductModel
import com.smb.core.extensions.DEFAULT_INT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

interface LocalSource {
    suspend fun getItems(): Flow<List<ProductModel>>
    suspend fun addNewItem(newItem: ProductModel)
    suspend fun clearItem(productId: String): Flow<Boolean>
    suspend fun clearAllItems(): Flow<Boolean>
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
            val builder = shoppingCart.toBuilder()
            val index = builder.itemsList.indexOfFirst { it.id == item.id }
            if (index != -1) {
                val storedItem = builder.itemsList[index]
                val updatedItem =
                    storedItem.toBuilder().setQuantity(storedItem.quantity.plus(item.quantity))
                builder.setItems(index, updatedItem)
            } else builder.addItems(item)
            builder.build()
        }
    }

    override suspend fun clearItem(productId: String): Flow<Boolean> =
        flow {
            dataStore.updateData { shoppingCart ->
                shoppingCart.toBuilder().apply {
                    this.itemsList.find {
                        it.id == productId
                    }?.let {
                        emit(this.itemsList.remove(it))
                    }
                }.build()
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun clearAllItems() =
        flow {
            dataStore.updateData { it.toBuilder().clearItems().build() }
            dataStore.data.map {
                emit(it.itemsCount == DEFAULT_INT)
            }
        }.flowOn(Dispatchers.IO)

}