package com.smb.ft_store.data.repository

import com.smb.core.data.Result
import com.smb.ft_store.data.source.StoreRemoteSource
import com.smb.ft_store.domain.model.ProductModel
import com.smb.ft_store.domain.repository.StoreRepository

class StoreRepositoryImpl(private val remoteSource: StoreRemoteSource) :
    StoreRepository {

    override suspend fun getProductList(): Result<List<ProductModel>> =
        remoteSource.getProductList()

    override suspend fun getProductDetails(productId: String): Result<ProductModel> =
        remoteSource.getProductDetails(productId)
}