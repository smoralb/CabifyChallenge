package com.smb.ft_store.ui.detail.mapper

import java.util.Currency
import java.util.Locale

interface DetailUiMapper {
    fun mapProductPrice(price: Float): String
}

class DetailUiMapperImpl : DetailUiMapper {

    private val currency = Currency.getInstance(Locale.getDefault())

    override fun mapProductPrice(price: Float): String =
        "$price ${currency.symbol}"
}