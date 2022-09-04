package com.smb.ft_checkout.domain.repository

import com.smb.ft_checkout.domain.model.CheckoutModel
import kotlinx.coroutines.flow.Flow

interface CheckoutRepository {
    fun getCheckoutItems(): Flow<List<CheckoutModel>>
}