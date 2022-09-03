package com.smb.ft_store.domain.repository

import com.smb.core.data.Result
import com.smb.ft_store.domain.model.ProductModel

interface StoreRepository {

    suspend fun getProductList(): Result<List<ProductModel>>
}