package com.smb.ft_checkout.ui

import com.smb.ft_checkout.domain.model.ProductModel

interface CheckoutDataMapper {
    fun mapCheckoutItems(): List<ProductModel>
}