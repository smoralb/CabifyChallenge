package com.smb.ft_checkout.data.source

import com.smb.core.data.Result
import com.smb.ft_checkout.domain.model.CheckoutModel

interface CheckoutLocalSource {
    fun getCheckoutItems(): Result<List<CheckoutModel>>
}

class CheckoutLocalSourceImpl : CheckoutLocalSource {

    override fun getCheckoutItems(): Result<List<CheckoutModel>> =
        Result.Success(emptyList<CheckoutModel>())

}