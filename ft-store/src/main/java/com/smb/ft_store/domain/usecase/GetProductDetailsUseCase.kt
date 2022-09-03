package com.smb.ft_store.domain.usecase

import com.smb.core.data.Result
import com.smb.core.domain.UseCase
import com.smb.ft_store.domain.model.ProductModel
import com.smb.ft_store.domain.repository.StoreRepository
import com.smb.ft_store.domain.usecase.GetProductDetailsUseCase.Params

interface GetProductDetailsUseCase : UseCase<Params, ProductModel> {
    data class Params(val id: String)
}

class GetProductDetailsUseCaseImpl(
    private val repository: StoreRepository
) : GetProductDetailsUseCase {

    override suspend fun invoke(params: Params): Result<ProductModel> =
        repository.getProductDetails(params.id)
}