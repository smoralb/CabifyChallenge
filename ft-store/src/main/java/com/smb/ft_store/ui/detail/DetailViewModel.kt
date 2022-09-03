package com.smb.ft_store.ui.detail

import androidx.lifecycle.MutableLiveData
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel
import com.smb.ft_store.domain.usecase.GetProductDetailsUseCase

class DetailViewModel(
    private val getProductDetailsUseCase: GetProductDetailsUseCase
) : BaseViewModel<DetailState>() {

    val name: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val description: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val price: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val image: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)

    fun init(productId: String) {
        execute {
            getProductDetailsUseCase(GetProductDetailsUseCase.Params(productId)).fold(
                handleSuccess = {
                    name update it.name
                    description update it.description
                    image update it.image
                    price update "${it.price} E"
                },
                handleError = {}
            )
        }

    }

    fun addToCart() {
        //Store in db && navigateUP && update cart
    }
}