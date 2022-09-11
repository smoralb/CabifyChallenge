package com.smb.ft_store.ui.detail

import android.content.Context
import com.smb.core.extensions.DEFAULT_FLOAT
import com.smb.core.extensions.DEFAULT_INT
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.test.BaseUnitTest
import com.smb.ft_store.domain.model.DiscountType.DISCOUNT_2_X_1
import com.smb.ft_store.domain.model.DiscountType.DISCOUNT_BULK_PURCHASE
import com.smb.ft_store.domain.model.DiscountType.NO_DISCOUNT
import com.smb.ft_store.ui.detail.mapper.DetailUiMapper
import com.smb.ft_store.ui.detail.mapper.DetailUiMapperImpl
import com.smb.ft_store.ui.productRequestMock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

class DetailUiMapperTest : BaseUnitTest() {

    @Mock
    private lateinit var mockContext: Context

    private lateinit var mapper: DetailUiMapper

    @BeforeEach
    fun setUp() {
        mapper = DetailUiMapperImpl(mockContext)
    }

    @Test
    fun `mapProductItem should return request model`() {

        val result = mapper.mapProductItem(
            EMPTY_STRING,
            EMPTY_STRING,
            EMPTY_STRING,
            DEFAULT_FLOAT,
            DEFAULT_INT
        )
        assertEquals(result, productRequestMock)
    }

    @TestFactory
    fun `mapdiscountType should return value to show for discountType`() = listOf(
        DISCOUNT_2_X_1,
        NO_DISCOUNT,
        DISCOUNT_BULK_PURCHASE
    ).map { testCase ->
        DynamicTest.dynamicTest(" $testCase") {
            whenever(mockContext.getString(any())).thenReturn(EMPTY_STRING)
            val result = mapper.mapDiscountType(testCase)
            assertTrue(result.isEmpty())
        }
    }
}