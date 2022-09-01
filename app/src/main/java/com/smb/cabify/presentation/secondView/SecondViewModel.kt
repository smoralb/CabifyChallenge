package com.smb.cabify.presentation.secondView

import androidx.lifecycle.MutableLiveData
import com.smb.cabify.domain.usecases.GetProductListUseCase
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel

class SecondViewModel(
    private val getProductListUseCase: GetProductListUseCase
) : BaseViewModel<SecondViewState>() {

    val title: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val description: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val publisher: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)

    fun init(code: String) {
        execute {
            getProductListUseCase(Unit).fold(
                handleError = {},
                handleSuccess = {
                    it.first { productDetails ->
                        productDetails.code == code
                    }.also { productDetails ->
                        title update productDetails.name
                        description update productDetails.name
                        publisher update productDetails.name
                    }
                }
            )
        }
    }
}