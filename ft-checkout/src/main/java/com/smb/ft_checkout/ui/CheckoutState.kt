package com.smb.ft_checkout.ui

import com.smb.core.presentation.base.BaseState

sealed class CheckoutState : BaseState() {
    object NavigateUp : CheckoutState()
    object HideTotalAmount: CheckoutState()
    object ShowTotalAmount: CheckoutState()
}