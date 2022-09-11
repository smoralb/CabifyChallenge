package com.smb.ft_store.ui.store

import android.content.Context
import com.smb.core.test.BaseUnitTest
import com.smb.ft_store.data.entity.ProductType.TSHIRT
import com.smb.ft_store.data.entity.ProductType.VOUCHER
import com.smb.ft_store.ui.productPresentationUiModelMock
import com.smb.ft_store.ui.productStoreDataItemMock
import com.smb.ft_store.ui.store.mapper.StoreUiMapper
import com.smb.ft_store.ui.store.mapper.StoreUiMapperImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

class StoreUiMapperTest : BaseUnitTest() {

    @Mock
    private lateinit var mockContext: Context

    private lateinit var mapper: StoreUiMapper

    @BeforeEach
    fun setUp() {
        mapper = StoreUiMapperImpl(mockContext)
    }

    @TestFactory
    fun `mapItems should return`() = listOf(
        listOf(productPresentationUiModelMock) to productStoreDataItemMock,
        listOf(productPresentationUiModelMock.copy(id = VOUCHER))
                to productStoreDataItemMock.copy(id = VOUCHER),
        listOf(productPresentationUiModelMock.copy(id = TSHIRT))
                to productStoreDataItemMock.copy(id = TSHIRT),
        listOf(productPresentationUiModelMock.copy(hasDiscount = false))
                to productStoreDataItemMock.copy(hasDiscount = false)
    ).map { testCase ->

        DynamicTest.dynamicTest(" ${testCase.first}") {
            val result = mapper.mapItems(testCase.first) {}

            assertEquals(result.first().id, testCase.second.id)
            assertEquals(result.first().name, testCase.second.name)
            assertEquals(result.first().price, testCase.second.price)
            assertEquals(result.first().image, testCase.second.image)
            assertEquals(result.first().description, testCase.second.description)
            assertEquals(result.first().hasDiscount, testCase.second.hasDiscount)
        }
    }

    @Test
    fun `mapErrorMessage should return error message as String`() {
        whenever(mockContext.getString(any<Int>())).thenReturn("Error")
        val result = mapper.mapErrorMessage()
        assertTrue(result.isNotBlank())
    }
}