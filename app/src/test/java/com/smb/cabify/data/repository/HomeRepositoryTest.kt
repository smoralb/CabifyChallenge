package com.smb.cabify.data.repository

import com.smb.cabify.data.source.HomeRemoteSource
import com.smb.cabify.domain.model.ProductModel
import com.smb.cabify.domain.repository.HomeRepository
import com.smb.core.data.Result
import com.smb.core.data.Result.Success
import com.smb.core.test.BaseUnitTest
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
class HomeRepositoryTest : BaseUnitTest() {

    @Mock
    private lateinit var remoteSource: HomeRemoteSource

    private lateinit var repository: HomeRepository

    @BeforeEach
    fun setUp() {
        repository = HomeRepositoryImpl(remoteSource)
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