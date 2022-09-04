package com.smb.ft_navigation

import android.content.Context
import com.smb.ft_checkout.CheckoutActivity
import com.smb.ft_store.ui.navigation.StoreNavigator

class StoreNavigatorImpl(
    private val context: Context
) : StoreNavigator {

    override fun navigateToShoppingCart() {
        with(context) { navigate(this, CheckoutActivity.newIntent(this)) }
    }
}