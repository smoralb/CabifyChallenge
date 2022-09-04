package com.smb.ft_checkout.domain.repository

import com.smb.core.data.Result
import com.smb.ft_checkout.domain.model.CheckoutModel

interface CheckoutRepository {
    fun getCheckoutItems(): Result<List<CheckoutModel>>
}