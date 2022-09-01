package com.smb.cabify.data.repository.mapper

import org.junit.jupiter.api.BeforeEach
import org.mockito.Mock

class SampleDataMapperImplTest {

    @Mock
    private lateinit var mapper: SampleDataMapper

    @BeforeEach
    fun setUp() {
        mapper = SampleDataMapperImpl()
    }
/*
    @TestFactory
    fun `toDomainModel should map entity(SampleApiResponseEntity) to model(SampleDataModel)` () = listOf(
        sampleResponseEntityValidMock to sampleResponseModelValidMock,
        sampleResponseEntityNullMock to sampleResponseModelEmptyMock
    ).map{ testCase ->
        DynamicTest.dynamicTest(" an return ${testCase.second}") {
            val result = mapper.toDomainModel(testCase.first)
            assertEquals(testCase.second, result)
        }
    }*/
}