package com.smb.ft_store.ui.detail

import androidx.lifecycle.MutableLiveData
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_store.domain.usecase.GetProductDetailsUseCase
import com.smb.ft_store.ui.detail.DetailState.NavigateUp
import com.smb.ft_store.ui.detail.mapper.DetailUiMapper
import com.smb.ft_store.ui.navigation.StoreNavigator

class DetailViewModel(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val mapper: DetailUiMapper,
    private val navigator: StoreNavigator
) : BaseViewModel<DetailState>() {

    val name: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val description: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val price: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val image: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)

    val onHeaderNavigationClickListener: () -> Unit = {
        viewState update NavigateUp
    }

    internal fun init(productId: String) {
        execute {
            getProductDetailsUseCase(GetProductDetailsUseCase.Params(productId)).fold(
                handleSuccess = {
                    name update it.name
                    description update it.description
                    image update it.image
                    price update mapper.mapProductPrice(it.price, it.currency)
                },
                handleError = {}
            )
        }

    }

    fun addToCart() {
        navigator.navigateToShoppingCart()
    }
}