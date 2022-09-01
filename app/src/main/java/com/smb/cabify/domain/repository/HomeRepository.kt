package com.smb.cabify.domain.repository

import com.smb.cabify.domain.model.ProductModel
import com.smb.core.data.Result

interface HomeRepository {

    suspend fun getProductList(): Result<List<ProductModel>>
}