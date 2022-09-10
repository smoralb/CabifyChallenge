package com.smb.ft_store.data

import com.smb.cabify.data.productsDataListEntityMock
import com.smb.cabify.data.productsDataListEntityNullMock
import com.smb.cabify.data.productsDataListNullEntityMock
import com.smb.ft_store.data.source.remote.mapper.StoreDataMapper
import com.smb.ft_store.data.source.remote.mapper.StoreDataMapperImpl
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
/*
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
    }*/
}