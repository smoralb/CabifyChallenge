package com.smb.ft_store.ui.detail

import androidx.lifecycle.MutableLiveData
import com.smb.core.domain.dataStore.model.ProductModel
import com.smb.core.domain.dataStore.repository.LocalRepository
import com.smb.core.extensions.DEFAULT_FLOAT
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_store.domain.usecase.GetProductDetailsUseCase
import com.smb.ft_store.ui.detail.DetailState.HideLoading
import com.smb.ft_store.ui.detail.DetailState.Loading
import com.smb.ft_store.ui.detail.DetailState.NavigateUp
import com.smb.ft_store.ui.detail.mapper.DetailUiMapper

class DetailViewModel(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val mapper: DetailUiMapper,
    private val repository: LocalRepository
) : BaseViewModel<DetailState>() {

    val name: MutableLiveData<String> = MutableLiveData()
    val description: MutableLiveData<String> = MutableLiveData()
    val price: MutableLiveData<String> = MutableLiveData()
    val image: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)

    private val quantity: MutableLiveData<Int> = MutableLiveData()
    private var itemPrice: Float = DEFAULT_FLOAT
    private var productId: String = EMPTY_STRING

    val onHeaderNavigationClickListener: () -> Unit = {
        viewState update NavigateUp
    }

    val onAddToCartListener: (Int) -> Unit = { quantity ->
        execute {
            repository.addNewItem(
                ProductModel(
                    id = productId,
                    title = name.value.orEmpty(),
                    image = image.value.orEmpty(),
                    price = itemPrice,
                    quantity = quantity
                )
            )
        }
    }

    internal fun init(productId: String) {
        this.productId = productId
        viewState update Loading
        execute {
            getProductDetailsUseCase(GetProductDetailsUseCase.Params(productId)).fold(
                handleSuccess = {
                    itemPrice = it.price
                    viewState update HideLoading
                    name update it.name
                    description update it.description
                    image update it.image
                    price update mapper.mapProductPrice(it.price)
                },
                handleError = {
                    viewState update HideLoading
                }
            )
        }

    }
}