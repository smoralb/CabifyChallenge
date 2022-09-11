package com.smb.ft_store.data.source.remote

import com.smb.core.data.safeApiCall
import com.smb.ft_store.data.service.StoreApi
import com.smb.ft_store.data.source.remote.mapper.StoreDataMapper
import com.smb.ft_store.domain.model.StoreProductModel

class StoreRemoteSourceImpl(
    private val api: StoreApi,
    private val mapper: StoreDataMapper
) : StoreRemoteSource {

    override suspend fun getProductList(): Result<List<StoreProductModel>> {
        return safeApiCall(
            { api.getProductList() },
            { entity -> mapper.toDomainModel(entity) }
        )
    }

    override suspend fun getProductDetails(productId: String): Result<StoreProductModel> =
        safeApiCall(
            { api.getProductList() },
            { entity -> mapper.toDomainModelDetails(productId, entity) }
        )
}