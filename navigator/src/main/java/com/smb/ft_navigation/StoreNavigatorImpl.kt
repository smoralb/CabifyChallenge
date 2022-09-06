package com.smb.ft_navigation

import android.content.Context
import com.smb.ft_checkout.CheckoutActivity
import com.smb.ft_store.ui.navigation.StoreNavigator

class StoreNavigatorImpl : StoreNavigator {

    override fun navigateToShoppingCart(context: Context) {
        with(context) { navigate(this, CheckoutActivity.newIntent(this)) }
    }
}