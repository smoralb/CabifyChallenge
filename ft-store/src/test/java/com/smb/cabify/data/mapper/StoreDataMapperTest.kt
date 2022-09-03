package com.smb.cabify.data.mapper

import com.smb.cabify.data.productDataModelEmptyMock
import com.smb.cabify.data.productDataModelMock
import com.smb.cabify.data.productsDataListEntityMock
import com.smb.cabify.data.productsDataListEntityNullMock
import com.smb.cabify.data.productsDataListNullEntityMock
import com.smb.cabify.data.repository.StoreDataMapper
import com.smb.cabify.data.repository.StoreDataMapperImpl
import com.smb.ft_store.domain.model.ProductModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class StoreDataMapperTest {

    private lateinit var mapper: StoreDataMapper

    @BeforeEach
    fun setUp() {
        mapper = StoreDataMapperImpl()
    }

    @TestFactory
    fun `mapper should map entity`() = listOf(
        productsDataListEntityMock to listOf(productDataModelMock),
        productsDataListEntityNullMock to listOf(productDataModelEmptyMock),
        productsDataListNullEntityMock to emptyList<ProductModel>()
    ).map { testcase ->
        DynamicTest.dynamicTest(" to model ${testcase.second}") {
            val result = mapper.toDomainModel(testcase.first)
            assertEquals(result, testcase.second)
        }
    }
}