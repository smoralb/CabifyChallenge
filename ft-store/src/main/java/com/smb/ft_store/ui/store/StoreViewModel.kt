package com.smb.ft_store.ui.store

import androidx.lifecycle.MutableLiveData
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_store.R
import com.smb.ft_store.domain.usecase.GetProductListUseCase
import com.smb.ft_store.ui.navigation.StoreNavigator
import com.smb.ft_store.ui.store.StoreState.HideLoading
import com.smb.ft_store.ui.store.StoreState.Loading
import com.smb.ft_store.ui.store.StoreState.NavigateToProductDetail
import com.smb.ft_store.ui.store.StoreState.NavigateToStore
import com.smb.ft_store.ui.store.adapter.StoreDataItems.StoreDataItem
import com.smb.ft_store.ui.store.mapper.StoreUiMapper

class StoreViewModel(
    private val getProductListUseCase: GetProductListUseCase,
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

    internal fun initialize() {
        getProductList()
    }

    internal fun navigateToStore() {
        navigator.navigateToShoppingCart()
    }

    private fun getProductList() {
        viewState update Loading
        execute {
            getProductListUseCase(Unit).fold(
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