package com.smb.core.presentation.base

import java.util.Currency
import java.util.Locale

interface BaseUiMapper {

    private val currency: Currency
        get() = Currency.getInstance(Locale.getDefault())

    fun mapAmount(price: Float) = "$price ${currency.symbol}"
}