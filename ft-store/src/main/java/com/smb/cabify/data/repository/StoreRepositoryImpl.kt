package com.smb.cabify.data.repository

import com.smb.core.data.Result
import com.smb.cabify.data.source.StoreRemoteSource
import com.smb.ft_store.domain.model.ProductModel
import com.smb.ft_store.domain.repository.StoreRepository

class StoreRepositoryImpl(private val remoteSource: StoreRemoteSource) :
    StoreRepository {

    override suspend fun getProductList(): Result<List<ProductModel>> =
        remoteSource.getProductList()

}