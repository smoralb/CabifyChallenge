package com.smb.cabify.domain.usecases

import com.smb.cabify.domain.model.ProductModel
import com.smb.cabify.domain.repository.HomeRepository
import com.smb.core.data.Result
import com.smb.core.domain.UseCase

interface GetProductListUseCase : UseCase<Unit, List<ProductModel>>

class GetProductListUseCaseImpl(
    private val repository: HomeRepository
) : GetProductListUseCase {

    override suspend fun invoke(params: Unit): Result<List<ProductModel>> =
        repository.getProductList()
}