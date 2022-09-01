package com.smb.cabify.data.source

import com.smb.cabify.data.HomeApi
import com.smb.cabify.data.repository.mapper.HomeDataMapper
import com.smb.cabify.domain.model.ProductModel
import com.smb.core.data.Result
import com.smb.core.data.safeApiCall

class HomeRemoteSourceImpl(
    private val api: HomeApi,
    private val mapper: HomeDataMapper
) : HomeRemoteSource {

    override suspend fun getProductList(): Result<List<ProductModel>> {
        return safeApiCall(
            { api.getProductList() },
            { entity -> mapper.toDomainModel(entity) }
        )
    }
}