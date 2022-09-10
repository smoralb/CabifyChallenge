package com.smb.ft_checkout.ui

import com.smb.core.presentation.base.BaseState

sealed class CheckoutState : BaseState() {
    object NavigateUp : CheckoutState()
    object ShowEmptyLayout: CheckoutState()
    object ShowTotalAmount: CheckoutState()
    object ShowCheckoutCompleted: CheckoutState()
}