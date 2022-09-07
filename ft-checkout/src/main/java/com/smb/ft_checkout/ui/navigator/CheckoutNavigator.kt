package com.smb.ft_checkout.ui.navigator

import android.content.Context
import com.smb.core.presentation.base.BaseNavigator

interface CheckoutNavigator : BaseNavigator {
    fun navigateBack(context: Context)
}