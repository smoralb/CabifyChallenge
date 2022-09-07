package com.smb.ft_checkout.domain

import com.smb.core.domain.model.ProductModelRequest
import com.smb.core.domain.model.ProductModelResponse
import kotlinx.coroutines.flow.Flow

interface CheckoutRepository {
    suspend fun getItems(): Flow<List<ProductModelResponse>>
    suspend fun addNewItem(newItem: ProductModelRequest): Flow<Boolean>
    suspend fun clearItem(productId: String)
}