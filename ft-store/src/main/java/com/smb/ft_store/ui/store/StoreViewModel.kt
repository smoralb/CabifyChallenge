package com.smb.ft_store.ui.store

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_store.domain.repository.StoreRepository
import com.smb.ft_store.ui.navigation.StoreNavigator
import com.smb.ft_store.ui.store.StoreState.HideLoading
import com.smb.ft_store.ui.store.StoreState.Loading
import com.smb.ft_store.ui.store.StoreState.NavigateToProductDetail
import com.smb.ft_store.ui.store.StoreState.NavigateToStore
import com.smb.ft_store.ui.store.adapter.StoreDataItems.StoreDataItem
import com.smb.ft_store.ui.store.mapper.StoreUiMapper

class StoreViewModel(
    private val repository: StoreRepository,
    private val mapper: StoreUiMapper,
    private val navigator: StoreNavigator
) : BaseViewModel<StoreState>() {

    val errorMessage: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val itemList: MutableLiveData<List<StoreDataItem>> = MutableLiveData(emptyList())

    private val onItemClickListener: (String) -> Unit = {
        viewState update NavigateToProductDetail(it)
    }

    val onHeaderClickListener: () -> Unit = {
        viewState update NavigateToStore
    }

    init {
        getProductList()
    }

    internal fun navigateToStore(context: Context) {
        navigator.navigateToShoppingCart(context)
    }

    private fun getProductList() {
        viewState update Loading
        execute {
            repository.getProductList().fold(
                handleSuccess = { productList ->
                    itemList update mapper.mapItems(productList, onItemClickListener)
                    viewState update HideLoading
                },
                handleError = {
                    errorMessage update "Error"
                    viewState update HideLoading
                }
            )
        }
    }
}