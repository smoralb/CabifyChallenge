package com.smb.ft_checkout.ui

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.smb.core.domain.dataStore.repository.LocalRepository
import com.smb.core.extensions.DEFAULT_INT
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_checkout.ui.CheckoutState.HideTotalAmount
import com.smb.ft_checkout.ui.CheckoutState.NavigateUp
import com.smb.ft_checkout.ui.CheckoutState.ShowTotalAmount
import com.smb.ft_checkout.ui.adapter.CheckoutDataItems.CheckoutDataItem
import com.smb.ft_checkout.ui.navigator.CheckoutNavigator
import kotlinx.coroutines.flow.collect

class CheckoutViewModel(
    private val mapper: CheckoutUiMapper,
    private val repository: LocalRepository,
    private val navigator: CheckoutNavigator
) : BaseViewModel<CheckoutState>() {

    val items = MutableLiveData<List<CheckoutDataItem>>(emptyList())
    val total = MutableLiveData<String>(EMPTY_STRING)
    val visibility = MutableLiveData<Int>(DEFAULT_INT)

    val onNavigationClickListener: () -> Unit = {
        viewState update NavigateUp
    }

    private val onItemClickListener: (String) -> Unit = {
        execute {
            repository.clearItem(it)
        }
    }

    internal fun initialize() {
        execute {
            repository.getItems().collect {
                items update mapper.mapCheckoutItems(it, onItemClickListener)
                if (it.isNotEmpty()) {
                    viewState update ShowTotalAmount
                    total update mapper.mapTotalPrice(it)
                } else viewState update HideTotalAmount
            }
        }
    }

    internal fun navigateBack(activity: Activity) {
        navigator.navigateBack(activity)
    }
}