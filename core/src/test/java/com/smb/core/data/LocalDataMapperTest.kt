package com.smb.core.data

import com.smb.core.DiscountType
import com.smb.core.data.source.mapper.LocalDataMapper
import com.smb.core.data.source.mapper.LocalDataMapperImpl
import com.smb.core.extensions.DEFAULT_FLOAT
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LocalDataMapperTest {

    private lateinit var mapper: LocalDataMapper

    @BeforeEach
    fun setUp() {
        mapper = LocalDataMapperImpl()
    }

    @Test
    fun `toDataModel should return item for dataStore`() {
        val result = mapper.toDataModel(productRequestDomainMock)
        assertEquals(result.name, productRequestDomainMock.name)
        assertEquals(result.quantity, productRequestDomainMock.quantity)
        assertEquals(result.image, productRequestDomainMock.image)
        assertEquals(result.price, productRequestDomainMock.price)
        assertFalse(result.hasDiscount)
        assertEquals(result.priceDiscount, DEFAULT_FLOAT)
        assertEquals(result.discountType, DiscountType.NO_DISCOUNT)
    }

    @Test
    fun `toDomainModel should return ProductModelResponse for the view`() {
        val result = mapper.toDomainModel(itemMock)
        assertEquals(result.name, productModelResponseMock.name)
        assertEquals(result.quantity, productModelResponseMock.quantity)
        assertEquals(result.image, productModelResponseMock.image)
        assertEquals(result.price, productModelResponseMock.price)
        assertFalse(result.hasDiscount)
        assertEquals(result.priceAfterDiscount, DEFAULT_FLOAT)
    }
}