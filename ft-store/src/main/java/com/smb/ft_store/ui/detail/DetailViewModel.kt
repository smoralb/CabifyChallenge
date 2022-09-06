package com.smb.ft_store.ui.detail

import androidx.lifecycle.MutableLiveData
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_store.domain.usecase.GetProductDetailsUseCase
import com.smb.ft_store.ui.detail.DetailState.HideLoading
import com.smb.ft_store.ui.detail.DetailState.Loading
import com.smb.ft_store.ui.detail.DetailState.NavigateUp
import com.smb.ft_store.ui.detail.mapper.DetailUiMapper
import com.smb.ft_store.ui.navigation.StoreNavigator

class DetailViewModel(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val mapper: DetailUiMapper
) : BaseViewModel<DetailState>() {

    val name: MutableLiveData<String> = MutableLiveData()
    val description: MutableLiveData<String> = MutableLiveData()
    val price: MutableLiveData<String> = MutableLiveData()
    val image: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    private val quantity: MutableLiveData<Int> = MutableLiveData()

    val onHeaderNavigationClickListener: () -> Unit = {
        viewState update NavigateUp
    }

    val onAddToCartListener: (Int) -> Unit = { quantity ->
        this.quantity update quantity

    }

    internal fun init(productId: String) {
        viewState update Loading
        execute {
            getProductDetailsUseCase(GetProductDetailsUseCase.Params(productId)).fold(
                handleSuccess = {
                    viewState update HideLoading
                    name update it.name
                    description update it.description
                    image update it.image
                    price update mapper.mapProductPrice(it.price, it.currency)
                },
                handleError = {
                    viewState update HideLoading
                }
            )
        }

    }
}