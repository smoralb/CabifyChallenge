package com.smb.ft_checkout.ui

import androidx.lifecycle.MutableLiveData
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_checkout.domain.model.ProductModel

class CheckoutViewModel : BaseViewModel<CheckoutState>() {

    val items = MutableLiveData<List<ProductModel>>()


}