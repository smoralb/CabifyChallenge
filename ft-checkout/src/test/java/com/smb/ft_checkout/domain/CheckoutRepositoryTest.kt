package com.smb.ft_checkout.domain

import com.smb.core.data.source.LocalSource
import com.smb.core.domain.model.ItemDiscountType.DISCOUNT_2_X_1
import com.smb.core.domain.model.ItemDiscountType.DISCOUNT_BULK_PURCHASE
import com.smb.core.domain.model.ItemDiscountType.NO_DISCOUNT
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.test.BaseUnitTest
import com.smb.ft_checkout.data.CheckoutRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class CheckoutRepositoryTest : BaseUnitTest() {

    @Mock
    private lateinit var localSource: LocalSource

    private lateinit var repository: CheckoutRepository

    @BeforeEach
    fun setUp() {
        repository = CheckoutRepositoryImpl(localSource)
    }

    @Test
    fun `getItems should call local source and retrieve list of items`() {
        runBlockingTest {
            repository.getItems()
            verify(localSource).getItems()
        }
    }

    @Test
    fun `addNewItem should call local source and add new items`() {
        runBlockingTest {
            repository.addNewItem(productRequestDomainMock)
            verify(localSource).addNewItem(productRequestDomainMock)
        }
    }

    @Test
    fun `clearItem should call local source and clear one item`() {
        runBlockingTest {
            repository.clearItem(EMPTY_STRING)
            verify(localSource).clearItem(EMPTY_STRING)
        }
    }

    @TestFactory
    fun `updateItem should call local source and update one item`() = listOf(
        DISCOUNT_2_X_1,
        DISCOUNT_BULK_PURCHASE,
        NO_DISCOUNT
    ).map { testCase ->
        DynamicTest.dynamicTest(" for type $testCase") {
            runBlockingTest {
                repository.updateItem(EMPTY_STRING, testCase)
                verify(localSource).updateItem(EMPTY_STRING, testCase)
            }
        }
    }

    @Test
    fun `clearAllItems should call local source and update one item`() {
        runBlockingTest {
            repository.clearAllItems()
            verify(localSource).clearAllItems()
        }
    }
}