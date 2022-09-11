package com.smb.ft_store.data.repository

import com.smb.ft_store.data.source.remote.StoreRemoteSource
import com.smb.core.test.BaseUnitTest
import com.smb.ft_store.domain.repository.StoreRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.mockito.Mock

@ExperimentalCoroutinesApi
class StoreRepositoryTest : BaseUnitTest() {

    @Mock
    private lateinit var remoteSource: StoreRemoteSource

    private lateinit var repository: StoreRepository
/*
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
    }*/
}