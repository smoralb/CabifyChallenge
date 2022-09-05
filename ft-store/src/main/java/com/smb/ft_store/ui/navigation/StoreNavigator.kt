package com.smb.ft_store.ui.navigation

import com.smb.core.presentation.base.BaseNavigator

interface StoreNavigator : BaseNavigator {
    fun navigateToShoppingCart()
}

sealed class StoreNavigatorArgs {
    data class AddProductArgs(
        val code: String,
        val name: String,
        val price: Int,
        val image: String,
        val quantity: Int
    ) : StoreNavigatorArgs()
}