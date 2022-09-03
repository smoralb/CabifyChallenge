package com.smb.ft_store.ui.detail.mapper

interface DetailUiMapper {
    fun mapProductPrice(price: Float, currency: String): String
}

class DetailUiMapperImpl(): DetailUiMapper {

    override fun mapProductPrice(price: Float, currency: String): String =
        "$price $currency"
}