package com.smb.ft_checkout.ui

import android.app.Activity
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.smb.core.domain.model.ItemDiscountType
import com.smb.core.extensions.DEFAULT_INT
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_checkout.domain.CheckoutRepository
import com.smb.ft_checkout.ui.CheckoutState.NavigateUp
import com.smb.ft_checkout.ui.CheckoutState.ShowCheckoutCompleted
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

    val onBtnPaymentClickListener: () -> Unit = {
        execute {
            repository.clearAllItems().onSuccess {
                viewState update ShowCheckoutCompleted
            }
        }
    }

    private val onItemClickListener: (String) -> Unit = {
        execute { repository.clearItem(it) }
    }

    private val onItemAddToCartClickListener: (String, ItemDiscountType) -> Unit =
        { productId, itemType ->
            execute {
                repository.updateItem(productId, itemType)
            }
        }

    fun initialize() {
        execute {
            repository.getItems().collect {
                items update mapper.mapCheckoutItems(
                    it,
                    onItemClickListener,
                    onItemAddToCartClickListener
                )
                if (it.isNotEmpty()) {
                    viewState update ShowTotalAmount
                    total update mapper.mapTotalPrice(it)
                } else viewState update ShowEmptyLayout
            }
        }
    }

    internal fun navigateBack(context: Context) {
        navigator.navigateBack(context)
    }
}