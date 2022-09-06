package com.smb.ft_checkout.ui

import com.smb.core.presentation.base.BaseState

sealed class CheckoutState : BaseState() {
    object NavigateUp : CheckoutState()
}