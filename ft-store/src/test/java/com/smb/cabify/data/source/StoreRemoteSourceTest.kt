package com.smb.cabify.data.source

import com.smb.cabify.data.productDataModelEmptyMock
import com.smb.cabify.data.productsDataListEntityMock
import com.smb.cabify.data.productsDataListEntityNullMock
import com.smb.cabify.data.productsDataListNullEntityMock
import com.smb.cabify.data.repository.StoreDataMapper
import com.smb.ft_store.data.service.StoreApi
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.test.BaseUnitTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody.Companion.toResponseBody
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

    private lateinit var remoteSource: com.smb.cabify.data.source.StoreRemoteSource

    @BeforeEach
    fun setUp() {
        remoteSource = com.smb.cabify.data.source.StoreRemoteSourceImpl(api, mapper)
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
                        listOf(productDataModelEmptyMock)
                    )

                val result = remoteSource.getProductList()

                when (testCase.isSuccessful) {
                    true -> assertTrue(result.isSuccess)
                    else -> assertTrue(result.isError)
                }
                verify(api, times(1)).getProductList()
                clearInvocations(api, mapper)
            }
        }
    }

}