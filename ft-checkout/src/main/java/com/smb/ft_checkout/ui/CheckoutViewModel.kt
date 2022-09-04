package com.smb.ft_checkout.ui

import androidx.lifecycle.MutableLiveData
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_checkout.domain.model.CheckoutModel
import com.smb.ft_checkout.domain.usecase.GetShoppingCartItemsUseCase

class CheckoutViewModel(
    private val getShoppingCartItemsUseCase: GetShoppingCartItemsUseCase
) : BaseViewModel<CheckoutState>() {

    val items = MutableLiveData<List<CheckoutModel>>()

    internal fun initialize() {
        execute {
            getShoppingCartItemsUseCase(Unit).fold(
                handleSuccess = {
                    items update it
                },
                handleError = {}
            )
        }
    }
}