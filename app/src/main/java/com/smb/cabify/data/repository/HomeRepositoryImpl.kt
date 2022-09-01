package com.smb.cabify.data.repository

import com.smb.cabify.data.source.HomeRemoteSource
import com.smb.cabify.domain.model.ProductModel
import com.smb.cabify.domain.repository.HomeRepository
import com.smb.core.data.Result

class HomeRepositoryImpl(private val remoteSource: HomeRemoteSource) : HomeRepository {

    override suspend fun getProductList(): Result<List<ProductModel>> =
        remoteSource.getProductList()

}