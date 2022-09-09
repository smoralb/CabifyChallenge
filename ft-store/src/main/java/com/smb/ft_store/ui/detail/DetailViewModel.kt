package com.smb.ft_store.ui.detail

import androidx.lifecycle.MutableLiveData
import com.smb.core.extensions.DEFAULT_FLOAT
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_store.domain.repository.StoreRepository
import com.smb.ft_store.ui.detail.DetailState.HideLoading
import com.smb.ft_store.ui.detail.DetailState.Loading
import com.smb.ft_store.ui.detail.DetailState.NavigateUp
import com.smb.ft_store.ui.detail.mapper.DetailUiMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class DetailViewModel(
    private val repository: StoreRepository,
    private val mapper: DetailUiMapper
) : BaseViewModel<DetailState>() {

    val name: MutableLiveData<String> = MutableLiveData()
    val description: MutableLiveData<String> = MutableLiveData()
    val price: MutableLiveData<String> = MutableLiveData()
    val image: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)

    private var itemPrice: Float = DEFAULT_FLOAT
    private var productId: String = EMPTY_STRING

    val onHeaderNavigationClickListener: () -> Unit = {
        viewState update NavigateUp
    }

    val onAddToCartListener: (Int) -> Unit = { quantity ->
        execute {
            repository.addNewItem(
                mapper.mapProductItem(
                    productId,
                    name.value,
                    image.value,
                    itemPrice,
                    quantity
                )
            ).onStart {
                viewState update Loading
                //Emulates API call delay
                delay(400)
            }.onCompletion { viewState update HideLoading }.collect()
        }
    }

    internal fun init(productId: String) {
        this.productId = productId
        viewState update Loading
        execute {
            repository.getProductDetails(productId).fold(
                handleSuccess = {
                    itemPrice = it.price
                    viewState update HideLoading
                    name update it.name
                    description update it.description
                    image update it.image
                    price update mapper.mapAmount(it.price)
                },
                handleError = {
                    viewState update HideLoading
                }
            )
        }

    }
}