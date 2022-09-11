package com.smb.ft_store.data.repository

import com.smb.core.data.source.LocalSource
import com.smb.core.domain.model.ProductRequest
import com.smb.ft_store.data.source.remote.StoreRemoteSource
import com.smb.ft_store.domain.model.StoreProductModel
import com.smb.ft_store.domain.repository.StoreRepository

class StoreRepositoryImpl(
    private val localSource: LocalSource,
    private val remoteSource: StoreRemoteSource
) : StoreRepository {

    override suspend fun getProductList(): Result<List<StoreProductModel>> =
        remoteSource.getProductList()

    override suspend fun getProductDetails(productId: String): Result<StoreProductModel> =
        remoteSource.getProductDetails(productId)

    override suspend fun addNewItem(newItem: ProductRequest): kotlin.Result<Unit> =
        localSource.addNewItem(newItem)
}