package com.smb.ft_store.data.source

import com.smb.core.data.Result
import com.smb.ft_store.domain.model.ProductModel

interface StoreRemoteSource {

    suspend fun getProductList(): Result<List<ProductModel>>
}