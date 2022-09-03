package com.smb.cabify.presentation.secondView

import androidx.lifecycle.MutableLiveData
import com.smb.cabify.domain.model.ProductModel
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel

class SecondViewModel : BaseViewModel<SecondViewState>() {

    val title: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val description: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val publisher: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)

    fun init(productModel: ProductModel) {
        title update productModel.code
        description update productModel.name
        publisher update productModel.name
    }
}