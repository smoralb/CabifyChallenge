package com.smb.ft_checkout.data

import com.smb.core.data.source.LocalSource
import com.smb.core.domain.model.ItemDiscountType
import com.smb.core.domain.model.ProductModelResponse
import com.smb.core.domain.model.ProductRequest
import com.smb.ft_checkout.domain.CheckoutRepository
import kotlinx.coroutines.flow.Flow

class CheckoutRepositoryImpl(
    private val localSource: LocalSource
) : CheckoutRepository {
    override suspend fun getItems(): Flow<List<ProductModelResponse>> =
        localSource.getItems()

    override suspend fun addNewItem(newItem: ProductRequest) =
        localSource.addNewItem(newItem)

    override suspend fun clearItem(productId: String) =
        localSource.clearItem(productId)

    override suspend fun updateItem(id: String, itemDiscountType: ItemDiscountType) =
        localSource.updateItem(id, itemDiscountType)
}