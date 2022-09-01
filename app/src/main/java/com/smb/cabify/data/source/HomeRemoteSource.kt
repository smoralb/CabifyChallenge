package com.smb.cabify.data.source

import com.smb.cabify.domain.model.ProductModel
import com.smb.core.data.Result

interface HomeRemoteSource {

    suspend fun getProductList(): Result<List<ProductModel>>
}