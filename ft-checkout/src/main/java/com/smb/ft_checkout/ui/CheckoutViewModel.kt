package com.smb.ft_checkout.ui

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.smb.core.domain.model.ProductModelRequest
import com.smb.core.extensions.DEFAULT_INT
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_checkout.domain.CheckoutRepository
import com.smb.ft_checkout.ui.CheckoutState.NavigateUp
import com.smb.ft_checkout.ui.CheckoutState.ShowEmptyLayout
import com.smb.ft_checkout.ui.CheckoutState.ShowTotalAmount
import com.smb.ft_checkout.ui.adapter.CheckoutDataItems.CheckoutDataItem
import com.smb.ft_checkout.ui.navigator.CheckoutNavigator
import kotlinx.coroutines.flow.collect

class CheckoutViewModel(
    private val mapper: CheckoutUiMapper,
    private val repository: CheckoutRepository,
    private val navigator: CheckoutNavigator
) : BaseViewModel<CheckoutState>() {

    val items = MutableLiveData<List<CheckoutDataItem>>(emptyList())
    val total = MutableLiveData<String>(EMPTY_STRING)
    val visibility = MutableLiveData<Int>(DEFAULT_INT)

    val onNavigationClickListener: () -> Unit = {
        viewState update NavigateUp
    }

    fun onPayClickListener ()  {
        //Emulate payment
    }

    private val onItemClickListener: (String) -> Unit = {
        execute { repository.clearItem(it) }
    }

    internal fun initialize() {
        execute {
            repository.getItems().collect {
                items update mapper.mapCheckoutItems(it, onItemClickListener)
                if (it.isNotEmpty()) {
                    viewState update ShowTotalAmount
                    total update mapper.mapTotalPrice(it)
                } else viewState update ShowEmptyLayout
            }
        }
    }

    internal fun navigateBack(activity: Activity) {
        navigator.navigateBack(activity)
    }
}