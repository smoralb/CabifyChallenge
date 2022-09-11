package com.smb.ft_store.data.source

import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.test.BaseUnitTest
import com.smb.ft_store.data.productPresentationDataModelMock
import com.smb.ft_store.data.productsDataListEntityMock
import com.smb.ft_store.data.productsDataListEntityNullMock
import com.smb.ft_store.data.productsDataListNullEntityMock
import com.smb.ft_store.data.service.StoreApi
import com.smb.ft_store.data.source.remote.StoreRemoteSource
import com.smb.ft_store.data.source.remote.StoreRemoteSourceImpl
import com.smb.ft_store.data.source.remote.mapper.StoreDataMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response.error
import retrofit2.Response.success

@ExperimentalCoroutinesApi
class StoreRemoteSourceTest : BaseUnitTest() {

    @Mock
    private lateinit var api: StoreApi

    @Mock
    private lateinit var mapper: StoreDataMapper

    private lateinit var remoteSource: StoreRemoteSource

    @BeforeEach
    fun setUp() {
        remoteSource = StoreRemoteSourceImpl(api, mapper)
    }

    @TestFactory
    fun `getProductList should return result`() = listOf(
        success(productsDataListEntityMock),
        success(productsDataListEntityNullMock),
        success(productsDataListNullEntityMock),
        error(500, EMPTY_STRING.toResponseBody())
    ).map { testCase ->
        DynamicTest.dynamicTest(" $testCase") {
            runBlockingTest {

                whenever(api.getProductList()).thenReturn(testCase)

                if (testCase.isSuccessful)
                    whenever(mapper.toDomainModel(any())).thenReturn(
                        listOf(productPresentationDataModelMock)
                    )

                val result = remoteSource.getProductList()

                when (testCase.isSuccessful) {
                    true -> assertTrue(result.isSuccess)
                    else -> assertTrue(result.isFailure)
                }
                if (testCase.isSuccessful)
                    assertEquals(result.getOrNull()?.first(), productPresentationDataModelMock)
                else assertEquals(result.getOrNull()?.first(), null)
                verify(api, times(1)).getProductList()
                clearInvocations(api, mapper)
            }
        }
    }

    @TestFactory
    fun `getProductDetails should return result`() = listOf(
        success(productsDataListEntityMock),
        success(productsDataListEntityNullMock),
        error(500, EMPTY_STRING.toResponseBody())
    ).map { testCase ->
        DynamicTest.dynamicTest(" $testCase") {
            runBlockingTest {

                whenever(api.getProductList()).thenReturn(
                    if (testCase.isSuccessful)
                        testCase
                    else null
                )

                if (testCase.isSuccessful)
                    whenever(mapper.toDomainModelDetails(any(), any()))
                        .thenReturn(productPresentationDataModelMock)

                val result = remoteSource.getProductDetails(EMPTY_STRING)

                when (testCase.isSuccessful) {
                    true -> assertTrue(result.isSuccess)
                    else -> assertTrue(result.isFailure)
                }
                if (testCase.isSuccessful)
                    assertEquals(result.getOrNull(), productPresentationDataModelMock)
                else assertEquals(result.getOrNull(), null)
                verify(api, times(1)).getProductList()
                clearInvocations(api, mapper)
            }
        }
    }

}