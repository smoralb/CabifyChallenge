package com.smb.ft_checkout

import android.content.Context
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.test.BaseViewModelUnitTest
import com.smb.ft_checkout.domain.CheckoutRepository
import com.smb.ft_checkout.ui.CheckoutState.NavigateUp
import com.smb.ft_checkout.ui.CheckoutState.ShowCheckoutCompleted
import com.smb.ft_checkout.ui.CheckoutState.ShowTotalAmount
import com.smb.ft_checkout.ui.CheckoutUiMapper
import com.smb.ft_checkout.ui.CheckoutViewModel
import com.smb.ft_checkout.ui.navigator.CheckoutNavigator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CheckoutViewModelTest : BaseViewModelUnitTest() {

    @Mock
    private lateinit var mockcontext: Context

    @Mock
    private lateinit var repository: CheckoutRepository

    @Mock
    private lateinit var navigator: CheckoutNavigator

    @Mock
    private lateinit var mapper: CheckoutUiMapper

    private lateinit var viewModel: CheckoutViewModel

    @BeforeEach
    fun setUp() {
        viewModel = CheckoutViewModel(mapper, repository, navigator)
    }

    @Test
    fun `onNavigationClickListener should modify ui state`() {
        viewModel.onNavigationClickListener()
        assertTrue(viewModel._viewState.value == NavigateUp)
    }

    @Test
    fun `initialize should return all items`() {
        runBlockingTest {
            whenever(repository.getItems()).thenReturn(flowOf(listOf(productModelResponseMock)))
            whenever(mapper.mapTotalPrice(any())).thenReturn(EMPTY_STRING)
            whenever(mapper.mapCheckoutItems(any(), any(), any()))
                .thenReturn(listOf(checkoutDataItemMock))
            viewModel.initialize()
            assertTrue(viewModel._viewState.value == ShowTotalAmount)
            assertEquals(EMPTY_STRING, viewModel.total.value)
        }
    }

    @Test
    fun `navigateBack should call navigator and navigate back`() {
        viewModel.navigateBack(mockcontext)
        verify(navigator).navigateBack(mockcontext)
    }
}