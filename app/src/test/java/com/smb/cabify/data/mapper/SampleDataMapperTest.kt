package com.smb.cabify.data.mapper

import com.smb.cabify.data.mocks.sampleApiResponseChildDetailsListNullEntityMock
import com.smb.cabify.data.mocks.sampleApiResponseNullEntityMock
import com.smb.cabify.data.mocks.sampleApiResponseValidEntityMock
import com.smb.cabify.data.mocks.sampleResponseChildModelMock
import com.smb.cabify.data.mocks.sampleResponseModelEmptyListMock
import com.smb.cabify.data.mocks.sampleResponseModelEmptyMock
import com.smb.cabify.data.repository.mapper.SampleDataMapper
import com.smb.cabify.data.repository.mapper.SampleDataMapperImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class SampleDataMapperTest {

    private lateinit var mapper: SampleDataMapper

    @BeforeEach
    fun setUp() {
        mapper = SampleDataMapperImpl()
    }

    @TestFactory
    fun `mapper should map entity`() = listOf(
        sampleApiResponseValidEntityMock to sampleResponseChildModelMock,
        sampleApiResponseNullEntityMock to sampleResponseModelEmptyListMock,
        sampleApiResponseChildDetailsListNullEntityMock to sampleResponseModelEmptyMock
    ).map { testcase ->
        DynamicTest.dynamicTest(" to model ${testcase.second}") {
            val result = mapper.toDomainModel(testcase.first)

            assertEquals(result, testcase.second)
        }
    }
}