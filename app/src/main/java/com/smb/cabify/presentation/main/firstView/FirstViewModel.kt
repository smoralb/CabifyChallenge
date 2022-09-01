package com.smb.cabify.presentation.main.firstView

import androidx.lifecycle.MutableLiveData
import com.smb.cabify.domain.usecases.GetSampleDataUseCase
import com.smb.cabify.presentation.main.firstView.adapter.SampleDataItems
import com.smb.cabify.presentation.main.firstView.mapper.FirstFragmentMapper
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.extensions.execute
import com.smb.core.extensions.update
import com.smb.core.presentation.base.BaseViewModel

class FirstViewModel(
    private val getSampleDataUseCase: GetSampleDataUseCase,
    private val mapper: FirstFragmentMapper
) : BaseViewModel<FirstViewState>() {

    val firstViewModelText: MutableLiveData<String> = MutableLiveData(EMPTY_STRING)
    val itemList: MutableLiveData<List<SampleDataItems.SampleDataItem>> =
        MutableLiveData(emptyList())

    private val onItemClickListener: (String) -> Unit = {
        _viewState update FirstViewState.NavigateToSecondFragment(isbn = it)
    }

    fun initialize() {
        getSampleData()
    }

    private fun getSampleData() {
        _viewState update FirstViewState.Loading
        execute {
            getSampleDataUseCase(Unit).fold(
                handleSuccess = {
                    itemList update mapper.mapItems(it.bookDetails, onItemClickListener)
                    _viewState update FirstViewState.HideLoading
                },
                handleError = {
                    firstViewModelText update "Error"
                    _viewState update FirstViewState.HideLoading
                }
            )
        }
    }
}