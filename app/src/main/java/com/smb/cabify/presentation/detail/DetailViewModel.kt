package com.smb.cabify.presentation.detail

import androidx.lifecycle.MutableLiveData
import com.smb.cabify.domain.model.ProductModel
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel

class DetailViewModel : BaseViewModel<DetailState>() {

    val name: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val description: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val price: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val code: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val image: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)

    fun init(productModel: ProductModel) {
        code update productModel.code
        name update productModel.name
        description update productModel.description
        image update productModel.image
    }

    fun addToCart() {
        //Store in db && navigateUP && update cart
    }
}