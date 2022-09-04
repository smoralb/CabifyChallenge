package com.smb.ft_checkout.domain.usecase

import com.smb.core.data.Result
import com.smb.core.domain.UseCase
import com.smb.ft_checkout.domain.model.CheckoutModel
import com.smb.ft_checkout.domain.repository.CheckoutRepository

interface GetShoppingCartItemsUseCase : UseCase<Unit, List<CheckoutModel>>

class GetShoppingCartItemsUseCaseImpl(
    private val repository: CheckoutRepository
) : GetShoppingCartItemsUseCase {
    override suspend fun invoke(params: Unit): Result<List<CheckoutModel>> =
        repository.getCheckoutItems()
}