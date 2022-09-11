package com.smb.ft_store.ui.detail

import com.smb.core.extensions.DEFAULT_INT
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.test.BaseViewModelUnitTest
import com.smb.ft_store.domain.model.DiscountType.DISCOUNT_2_X_1
import com.smb.ft_store.domain.repository.StoreRepository
import com.smb.ft_store.ui.detail.DetailState.HideLoading
import com.smb.ft_store.ui.detail.DetailState.Loading
import com.smb.ft_store.ui.detail.DetailState.NavigateUp
import com.smb.ft_store.ui.detail.mapper.DetailUiMapper
import com.smb.ft_store.ui.productPresentationUiModelMock
import com.smb.ft_store.ui.productRequestMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class DetailViewModelTest : BaseViewModelUnitTest() {

    @Mock
    private lateinit var repository: StoreRepository

    @Mock
    private lateinit var mapper: DetailUiMapper

    private lateinit var viewModel: DetailViewModel

    @BeforeEach
    fun setUp() {
        viewModel = DetailViewModel(repository, mapper)
    }

    @Test
    fun `onHeaderNavigationClickListener should update state to NavigateUp`() {
        viewModel.onHeaderNavigationClickListener()
        assertEquals(NavigateUp, viewModel._viewState.value)
    }

    @Test
    fun `onAddToCartListener should add new item`() =
        runTest {
            whenever(mapper.mapProductItem(any<String>(), any(), any(), any(), any()))
                .thenReturn(productRequestMock)
            whenever(repository.addNewItem(any())).thenReturn(Result.success(any()))

            viewModel.onAddToCartListener(DEFAULT_INT)
            delay(400)
            assertEquals(Loading, viewModel._viewState.value)
        }


    @TestFactory
    fun `init should return the list of items with result`() = listOf(
        Result.success(productPresentationUiModelMock),
        Result.failure(Exception())
    ).map { testCase ->
        DynamicTest.dynamicTest(" $testCase") {
            runTest {
                whenever(repository.getProductDetails(EMPTY_STRING)).thenReturn(testCase)
                if (testCase.isSuccess) {
                    whenever(mapper.mapAmount(any())).thenReturn(EMPTY_STRING)
                    whenever(mapper.mapDiscountType(any())).thenReturn(DISCOUNT_2_X_1.name)
                }

                viewModel.init(EMPTY_STRING)

                if (testCase.isSuccess) {
                    assertTrue(viewModel._viewState.value == HideLoading)
                    assertEquals(viewModel.name.value, EMPTY_STRING)
                    assertEquals(viewModel.description.value, EMPTY_STRING)
                    assertEquals(viewModel.image.value, EMPTY_STRING)
                    assertEquals(viewModel.price.value, EMPTY_STRING)
                    assertEquals(viewModel.hasDiscount.value, true)
                    assertEquals(viewModel.discountType.value, DISCOUNT_2_X_1.name)
                } else {
                    assertTrue(viewModel._viewState.value == HideLoading)
                }
            }
        }
    }
}