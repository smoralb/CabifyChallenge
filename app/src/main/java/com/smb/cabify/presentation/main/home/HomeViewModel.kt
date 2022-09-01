package com.smb.cabify.presentation.main.home

import androidx.lifecycle.MutableLiveData
import com.smb.cabify.domain.usecases.GetSampleDataUseCase
import com.smb.cabify.presentation.main.home.adapter.HomeDataItems
import com.smb.cabify.presentation.main.home.mapper.FirstFragmentMapper
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel

class HomeViewModel(
    private val getSampleDataUseCase: GetSampleDataUseCase,
    private val mapper: FirstFragmentMapper
) : BaseViewModel<HomeViewState>() {

    val firstViewModelText: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val itemList: MutableLiveData<List<HomeDataItems.HomeDataItem>> =
        MutableLiveData(emptyList())

    private val onItemClickListener: (String) -> Unit = {
        _viewState update HomeViewState.NavigateToSecondFragment(isbn = it)
    }

    fun initialize() {
        getSampleData()
    }

    private fun getSampleData() {
        _viewState update HomeViewState.Loading
        execute {
            getSampleDataUseCase(Unit).fold(
                handleSuccess = {
                    itemList update mapper.mapItems(it.bookDetails, onItemClickListener)
                    _viewState update HomeViewState.HideLoading
                },
                handleError = {
                    firstViewModelText update "Error"
                    _viewState update HomeViewState.HideLoading
                }
            )
        }
    }
}