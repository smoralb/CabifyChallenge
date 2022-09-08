package com.smb.core.data.source

import androidx.datastore.core.DataStore
import com.smb.core.ShoppingCart
import com.smb.core.data.source.mapper.LocalDataMapper
import com.smb.core.domain.model.ItemDiscountType
import com.smb.core.domain.model.ItemDiscountType.DISCOUNT_2_X_1
import com.smb.core.domain.model.ItemDiscountType.DISCOUNT_BULK_PURCHASE
import com.smb.core.domain.model.ProductModelRequest
import com.smb.core.domain.model.ProductModelResponse
import com.smb.core.extensions.DEFAULT_INT
import kotlin.math.abs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

interface LocalSource {
    suspend fun getItems(): Flow<List<ProductModelResponse>>
    suspend fun addNewItem(newItem: ProductModelRequest): Flow<Boolean>
    suspend fun clearItem(productId: String)
    suspend fun clearAllItems(): Flow<Boolean>
    suspend fun updateItem(id: String, itemDiscountType: ItemDiscountType)
}

class LocalSourceImpl(
    private val dataStore: DataStore<ShoppingCart>,
    private val mapper: LocalDataMapper
) : LocalSource {

    override suspend fun getItems(): Flow<List<ProductModelResponse>> =
        flow {
            dataStore.data.collect {
                var items = emptyList<ProductModelResponse>()
                if (it.itemsCount != 0) {
                    items = it.itemsList.map { item -> mapper.toDomainModel(item) }
                }
                emit(items)
            }
        }

    override suspend fun addNewItem(newItem: ProductModelRequest) =
        flow {
            val item = mapper.toDataModel(newItem)
            dataStore.updateData { shoppingCart ->
                val builder = shoppingCart.toBuilder()
                val index = builder.itemsList.indexOfFirst { it.id == item.id }
                if (index != -1) {
                    val storedItem = builder.itemsList[index]
                    val updatedItem =
                        storedItem.toBuilder()
                            .setQuantity(storedItem.quantity.plus(item.quantity))
                    builder.setItems(index, updatedItem)
                } else builder.addItems(item)
                builder.build()
            }
            emit(true)
        }


    override suspend fun clearItem(productId: String) {
        dataStore.updateData { shoppingCart ->
            val builder = shoppingCart.toBuilder()
            val index = builder.itemsList.indexOfFirst { it.id == productId }
            builder.removeItems(index).build()
        }
    }

    override suspend fun updateItem(id: String, itemDiscountType: ItemDiscountType) {
        dataStore.updateData { shoppingCart ->
            val builder = shoppingCart.toBuilder()
            val index = builder.itemsList.indexOfFirst { it.id == id }
            val itemQuantity = builder.itemsList[index].quantity
            val updatedItem = builder.itemsList[index].toBuilder()
                .setQuantity(updateQuantity(itemDiscountType, itemQuantity))
            updatedItem.hasDiscount = false
            builder.setItems(index, updatedItem)
            builder.build()
        }
    }

    override suspend fun clearAllItems() =
        flow {
            dataStore.updateData { it.toBuilder().clearItems().build() }
            dataStore.data.map {
                emit(it.itemsCount == DEFAULT_INT)
            }
        }.flowOn(Dispatchers.IO)

    private fun updateQuantity(itemDiscountType: ItemDiscountType, quantity: Int): Int =
        when (itemDiscountType) {
            DISCOUNT_2_X_1 -> quantity.plus(quantity % 2)
            DISCOUNT_BULK_PURCHASE -> quantity.plus(abs(quantity % 3 - 3))
        }
}
