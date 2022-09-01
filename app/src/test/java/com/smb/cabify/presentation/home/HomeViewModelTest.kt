package com.smb.cabify.presentation.home

import com.smb.cabify.domain.usecases.GetProductListUseCase
import com.smb.cabify.presentation.home.HomeViewState.HideLoading
import com.smb.cabify.presentation.home.mapper.HomePresentationMapper
import com.smb.cabify.presentation.productPresentationModelEmptyMock
import com.smb.cabify.presentation.productPresentationModelMock
import com.smb.core.data.Result.Error
import com.smb.core.data.Result.Success
import com.smb.core.test.BaseViewModelUnitTest
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
class HomeViewModelTest : BaseViewModelUnitTest() {

    @Mock
    private lateinit var getProductListUseCase: GetProductListUseCase

    @Mock
    private lateinit var mapper: HomePresentationMapper

    private lateinit var viewModel: HomeViewModel

    @BeforeEach
    fun setUp() {
        viewModel = HomeViewModel(getProductListUseCase, mapper)
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
                    verify(mapper).mapItems(any(), any())
                } else if (testCase.isError) {
                    assertTrue(viewModel.firstViewModelText.value == "Error")
                }

                assertTrue(viewModel._viewState.value == HideLoading)
            }
            clearInvocations(getProductListUseCase, mapper)
        }
    }


}