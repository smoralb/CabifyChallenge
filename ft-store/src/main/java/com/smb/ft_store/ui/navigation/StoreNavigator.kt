package com.smb.ft_store.ui.navigation

import android.content.Context
import com.smb.core.presentation.base.BaseNavigator

interface StoreNavigator : BaseNavigator {
    fun navigateToShoppingCart(context: Context)
}