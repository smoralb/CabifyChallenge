package com.smb.ft_store.domain.usecase

import com.smb.core.data.Result
import com.smb.core.domain.UseCase
import com.smb.ft_store.domain.model.ProductModel
import com.smb.ft_store.domain.repository.StoreRepository

interface GetProductListUseCase : UseCase<Unit, List<ProductModel>>

class GetProductListUseCaseImpl(
    private val repository: StoreRepository
) : GetProductListUseCase {

    override suspend fun invoke(params: Unit): Result<List<ProductModel>> =
        repository.getProductList()
}