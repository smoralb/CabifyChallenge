package com.smb.cabify.presentation.home

import androidx.lifecycle.MutableLiveData
import com.smb.cabify.domain.usecases.GetProductListUseCase
import com.smb.cabify.presentation.home.HomeViewState.HideLoading
import com.smb.cabify.presentation.home.HomeViewState.Loading
import com.smb.cabify.presentation.home.adapter.HomeDataItems.HomeDataItem
import com.smb.cabify.presentation.home.mapper.HomePresentationMapper
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel

class HomeViewModel(
    private val getProductListUseCase: GetProductListUseCase,
    private val mapper: HomePresentationMapper
) : BaseViewModel<HomeViewState>() {

    val firstViewModelText: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val itemList: MutableLiveData<List<HomeDataItem>> = MutableLiveData(emptyList())

    fun initialize() {
        getProductList()
    }

    private fun getProductList() {
        viewState update Loading
        execute {
            getProductListUseCase(Unit).fold(
                handleSuccess = { productList ->
                    itemList update mapper.mapItems(productList)
                    viewState update HideLoading
                },
                handleError = {
                    firstViewModelText update "Error"
                    viewState update HideLoading
                }
            )
        }
    }
}