package com.smb.ft_checkout.data.repository

import com.smb.core.data.Result
import com.smb.ft_checkout.data.source.CheckoutLocalSource
import com.smb.ft_checkout.domain.model.CheckoutModel
import com.smb.ft_checkout.domain.repository.CheckoutRepository

class CheckoutRepositoryImpl(
    private val localSource: CheckoutLocalSource
): CheckoutRepository {

    override fun getCheckoutItems(): Result<List<CheckoutModel>> =
        localSource.getCheckoutItems()
}