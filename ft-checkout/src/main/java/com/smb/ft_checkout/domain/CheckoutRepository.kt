package com.smb.ft_checkout.domain

import com.smb.core.domain.model.ItemDiscountType
import com.smb.core.domain.model.ProductModelResponse
import com.smb.core.domain.model.ProductRequest
import kotlinx.coroutines.flow.Flow

interface CheckoutRepository {
    suspend fun getItems(): Flow<List<ProductModelResponse>>
    suspend fun addNewItem(newItem: ProductRequest): Result<Unit>
    suspend fun updateItem(id: String, itemDiscountType: ItemDiscountType)
    suspend fun clearItem(productId: String)
    suspend fun clearAllItems(): Result<Unit>
}