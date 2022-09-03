package com.smb.cabify.ui.store

import com.smb.cabify.ui.productPresentationModelEmptyMock
import com.smb.cabify.ui.productPresentationModelMock
import com.smb.core.data.Result.Error
import com.smb.core.data.Result.Success
import com.smb.core.test.BaseViewModelUnitTest
import com.smb.ft_store.domain.usecase.GetProductListUseCase
import com.smb.ft_store.ui.store.StoreState.HideLoading
import com.smb.ft_store.ui.store.StoreViewModel
import com.smb.ft_store.ui.store.mapper.StoreUiMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class StoreViewModelTest : BaseViewModelUnitTest() {

    @Mock
    private lateinit var getProductListUseCase: GetProductListUseCase

    @Mock
    private lateinit var mapper: StoreUiMapper

    private lateinit var viewModel: StoreViewModel

    @BeforeEach
    fun setUp() {
        viewModel = StoreViewModel(getProductListUseCase, mapper)
    }

    @TestFactory
    fun `getProductList should return items with result`() = listOf(
        Success(listOf(productPresentationModelMock)),
        Success(listOf(productPresentationModelEmptyMock)),
        Error()
    ).map { testCase ->
        DynamicTest.dynamicTest(" $testCase") {
            runBlockingTest {
                whenever(getProductListUseCase(any())).thenReturn(testCase)

                viewModel.initialize()

                if (testCase.isSuccess) {
                    verify(mapper).mapItems(any())
                } else if (testCase.isError) {
                    assertTrue(viewModel.errorMessage.value == "Error")
                }

                assertTrue(viewModel._viewState.value == HideLoading)
            }
            clearInvocations(getProductListUseCase, mapper)
        }
    }


}