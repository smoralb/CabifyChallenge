package com.smb.ft_store.data.source.remote

import com.smb.core.data.Result
import com.smb.ft_store.domain.model.StoreProductModel

interface StoreRemoteSource {

    suspend fun getProductList(): Result<List<StoreProductModel>>

    suspend fun getProductDetails(productId: String): Result<StoreProductModel>
}