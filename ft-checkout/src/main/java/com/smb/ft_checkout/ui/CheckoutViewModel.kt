package com.smb.ft_checkout.ui

import androidx.lifecycle.MutableLiveData
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_checkout.domain.repository.CheckoutRepository
import com.smb.ft_checkout.ui.adapter.CheckoutDataItems
import kotlinx.coroutines.flow.collect

class CheckoutViewModel(
    private val mapper: CheckoutUiMapper,
    private val repository: CheckoutRepository
) : BaseViewModel<CheckoutState>() {

    val items = MutableLiveData<List<CheckoutDataItems.CheckoutDataItem>>()
    private val onItemClickListener: (String) -> Unit = {

    }

    internal fun initialize() {
        execute {
            repository.getCheckoutItems().collect {
                items update mapper.mapCheckoutItems(it, onItemClickListener)
            }
        }
    }
}