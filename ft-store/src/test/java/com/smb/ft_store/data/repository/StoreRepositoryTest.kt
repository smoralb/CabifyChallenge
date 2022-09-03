package com.smb.ft_store.data.repository

import com.smb.ft_store.data.source.StoreRemoteSource
import com.smb.core.data.Result
import com.smb.core.data.Result.Success
import com.smb.core.test.BaseUnitTest
import com.smb.ft_store.domain.model.ProductModel
import com.smb.ft_store.domain.repository.StoreRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class StoreRepositoryTest : BaseUnitTest() {

    @Mock
    private lateinit var remoteSource: StoreRemoteSource

    private lateinit var repository: StoreRepository

    @BeforeEach
    fun setUp() {
        repository = StoreRepositoryImpl(remoteSource)
    }

    @TestFactory
    fun `getProductList should call remoteSource with result`() = listOf(
        Success(emptyList<ProductModel>()), Result.Error()
    ).map { testCase ->
        DynamicTest.dynamicTest(" $testCase") {
            runBlockingTest {
                whenever(repository.getProductList()).thenReturn(testCase)

                val result = repository.getProductList()

                assertEquals(testCase, result)

                clearInvocations(remoteSource)
            }
        }
    }
}