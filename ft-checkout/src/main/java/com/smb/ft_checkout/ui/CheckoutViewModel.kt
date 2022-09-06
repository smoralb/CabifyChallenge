package com.smb.ft_checkout.ui

import androidx.lifecycle.MutableLiveData
import com.smb.core.domain.dataStore.model.CheckoutModel
import com.smb.core.domain.dataStore.repository.CheckoutRepository
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_checkout.ui.adapter.CheckoutDataItems.CheckoutDataItem
import kotlinx.coroutines.flow.collect

class CheckoutViewModel(
    private val mapper: CheckoutUiMapper,
    private val repository: CheckoutRepository
) : BaseViewModel<CheckoutState>() {

    val items = MutableLiveData<List<CheckoutDataItem>>(emptyList())

    private val onItemClickListener: (String) -> Unit = {

    }

    internal fun initialize() {
        execute {
            repository.addNewItem(
                CheckoutModel(
                    id = "id",
                    title = "title",
                    image = "https://images.unsplash.com/photo-1516390118834-21602d501886?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1836&q=80",
                    price = 12f,
                    quantity = 1
                )
            )
            repository.getCheckoutItems().collect {
                items update mapper.mapCheckoutItems(it, onItemClickListener)
            }
        }
    }
}