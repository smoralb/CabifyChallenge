package com.smb.ft_checkout.data.repository

import com.smb.ft_checkout.data.source.CheckoutLocalSource
import com.smb.ft_checkout.domain.model.CheckoutModel
import com.smb.ft_checkout.domain.repository.CheckoutRepository
import kotlinx.coroutines.flow.Flow

class CheckoutRepositoryImpl(
    private val localSource: CheckoutLocalSource
) : CheckoutRepository {

    override fun getCheckoutItems(): Flow<List<CheckoutModel>> =
        localSource.getCheckoutItems()
}