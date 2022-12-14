package com.smb.ft_store.ui.store

import android.content.Context
import com.smb.core.test.BaseViewModelUnitTest
import com.smb.ft_store.domain.repository.StoreRepository
import com.smb.ft_store.ui.navigation.StoreNavigator
import com.smb.ft_store.ui.productPresentationUiModelMock
import com.smb.ft_store.ui.store.StoreState.HideLoading
import com.smb.ft_store.ui.store.StoreState.NavigateToStore
import com.smb.ft_store.ui.store.mapper.StoreUiMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class StoreViewModelTest : BaseViewModelUnitTest() {

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var repository: StoreRepository

    @Mock
    private lateinit var mapper: StoreUiMapper

    @Mock
    private lateinit var navigator: StoreNavigator

    private lateinit var viewModel: StoreViewModel

    @BeforeEach
    fun setUp() {
        viewModel = StoreViewModel(repository, mapper, navigator)
    }

    @TestFactory
    fun `getProductList should return items with result`() = listOf(
        Result.success(listOf(productPresentationUiModelMock)),
        Result.failure(Exception())
    ).map { testCase ->
        DynamicTest.dynamicTest(" $testCase") {
            runBlockingTest {
                whenever(repository.getProductList()).thenReturn(testCase)

                viewModel.initialize()

                if (testCase.isSuccess) {
                    verify(mapper).mapItems(any(), any())
                } else if (testCase.isFailure) {
                    verify(mapper).mapErrorMessage()
                }

                assertTrue(viewModel._viewState.value == HideLoading)
            }
            clearInvocations(repository, mapper)
        }
    }

    @Test
    fun `navigateToStore should navigate to Store`() {
        viewModel.navigateToCheckout(context)
        verify(navigator).navigateToShoppingCart(context)
    }

    @Test
    fun `onHeaderClickListener should modify ui state`() {
        viewModel.onHeaderClickListener()
        assertTrue(viewModel._viewState.value == NavigateToStore)
    }


}