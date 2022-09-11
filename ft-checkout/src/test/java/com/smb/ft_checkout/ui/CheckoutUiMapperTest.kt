package com.smb.ft_checkout.ui

import android.content.Context
import com.smb.core.domain.model.ItemDiscountType
import com.smb.core.extensions.DEFAULT_FLOAT
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.test.BaseUnitTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

class CheckoutUiMapperTest : BaseUnitTest() {

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var onItemClickListener: (String) -> Unit

    @Mock
    private lateinit var onOfferClickListener: (String, ItemDiscountType) -> Unit

    private lateinit var mapper: CheckoutUiMapper
    private val items = listOf(productModelResponseMock)

    @BeforeEach
    fun setUp() {
        mapper = CheckoutUiMapperImpl(mockContext)
    }

    @Test
    fun `mapCheckoutItems should return Ui item`() {
        whenever(mockContext.getString(any())).thenReturn(EMPTY_STRING)

        val result = mapper.mapCheckoutItems(items, {}, { _, _ -> })
        assertEquals(result.first().id, checkoutDataItemMock.id)
        assertEquals(result.first().title, checkoutDataItemMock.title)
        assertEquals(result.first().price, checkoutDataItemMock.price)
        assertEquals(result.first().priceDiscount, checkoutDataItemMock.priceDiscount)
        assertEquals(result.first().image, checkoutDataItemMock.image)
        assertEquals(result.first().quantity, checkoutDataItemMock.quantity)
        assertEquals(result.first().hasDiscount, checkoutDataItemMock.hasDiscount)
        assertEquals(result.first().showPriceDiscount, checkoutDataItemMock.showPriceDiscount)
        assertEquals(result.first().titleDiscount, checkoutDataItemMock.titleDiscount)
        assertEquals(result.first().itemDiscountType, checkoutDataItemMock.itemDiscountType)

    }

    @Test
    fun `mapTotalPrice should return total amount`() {
        val result = mapper.mapTotalPrice(items)
        assertTrue(result == "$DEFAULT_FLOAT $")
    }
}