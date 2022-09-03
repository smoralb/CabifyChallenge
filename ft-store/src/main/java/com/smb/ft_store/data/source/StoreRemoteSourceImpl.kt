package com.smb.ft_store.data.source

import com.smb.core.data.Result
import com.smb.core.data.safeApiCall
import com.smb.ft_store.data.repository.StoreDataMapper
import com.smb.ft_store.data.service.StoreApi
import com.smb.ft_store.domain.model.ProductModel

class StoreRemoteSourceImpl(
    private val api: StoreApi,
    private val mapper: StoreDataMapper
) : StoreRemoteSource {

    override suspend fun getProductList(): Result<List<ProductModel>> {
        return safeApiCall(
            { api.getProductList() },
            { entity -> mapper.toDomainModel(entity) }
        )
    }

    override suspend fun getProductDetails(productId: String): Result<ProductModel> =
        safeApiCall(
            { api.getProductList() },
            { entity -> mapper.toDomainModelDetails(productId, entity) }
        )
}