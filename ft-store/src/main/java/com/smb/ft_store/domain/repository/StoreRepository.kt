package com.smb.ft_store.domain.repository

import com.smb.core.data.Result
import com.smb.core.domain.model.ProductRequest
import com.smb.ft_store.domain.model.StoreProductModel
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    suspend fun getProductList(): Result<List<StoreProductModel>>
    suspend fun getProductDetails(productId: String): Result<StoreProductModel>
    suspend fun addNewItem(newItem: ProductRequest): Flow<Boolean>
}