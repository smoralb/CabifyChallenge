package com.smb.ft_navigation

import android.content.Context
import com.smb.ft_checkout.ui.navigator.CheckoutNavigator
import com.smb.ft_store.StoreActivity

class CheckoutNavigatorImpl : CheckoutNavigator {

    override fun navigateBack(context: Context) {
        with(context) { navigateClearTop(context, StoreActivity.newIntent(this)) }
    }
}