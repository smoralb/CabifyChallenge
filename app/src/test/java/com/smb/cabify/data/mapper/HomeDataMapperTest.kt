package com.smb.cabify.data.mapper

import com.smb.cabify.data.productDataModelEmptyMock
import com.smb.cabify.data.productDataModelMock
import com.smb.cabify.data.productsDataListEntityMock
import com.smb.cabify.data.productsDataListEntityNullMock
import com.smb.cabify.data.productsDataListNullEntityMock
import com.smb.cabify.data.repository.mapper.HomeDataMapper
import com.smb.cabify.data.repository.mapper.HomeDataMapperImpl
import com.smb.cabify.domain.model.ProductModel
import com.smb.core.test.BaseUnitTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class HomeDataMapperTest {

    private lateinit var mapper: HomeDataMapper

    @BeforeEach
    fun setUp() {
        mapper = HomeDataMapperImpl()
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