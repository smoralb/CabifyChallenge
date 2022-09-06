package com.smb.core.domain.dataStore.repository

import com.smb.core.domain.dataStore.model.ProductModel
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun getItems(): Flow<List<ProductModel>>
    suspend fun addNewItem(newItem: ProductModel)
    suspend fun clearItem(productId: String)
}