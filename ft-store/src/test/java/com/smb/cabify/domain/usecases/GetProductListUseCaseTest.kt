package com.smb.cabify.domain.usecases

import com.smb.cabify.domain.productDomainModelEmptyMock
import com.smb.cabify.domain.productDomainModelMock
import com.smb.core.data.Result.Error
import com.smb.core.data.Result.Success
import com.smb.core.test.BaseUnitTest
import com.smb.ft_store.domain.repository.StoreRepository
import com.smb.ft_store.domain.usecase.GetProductListUseCase
import com.smb.ft_store.domain.usecase.GetProductListUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GetProductListUseCaseTest : BaseUnitTest() {

    @Mock
    private lateinit var repositoryImpl: StoreRepository

    private lateinit var useCase: GetProductListUseCase

    @BeforeEach
    fun setUp() {
        useCase = GetProductListUseCaseImpl(repositoryImpl)
    }

    @TestFactory
    fun `GetProductListUseCase should return `() = listOf(
        Success(listOf(productDomainModelMock)),
        Success(listOf(productDomainModelEmptyMock)),
        Error()
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {
            runBlockingTest {
                whenever(repositoryImpl.getProductList()).thenReturn(testCase)

                val result = useCase(Unit)

                assertEquals(result.isError, testCase.isError)
                assertEquals(result.isSuccess, testCase.isSuccess)
                verify(repositoryImpl, times(1)).getProductList()
                verifyNoMoreInteractions(repositoryImpl)
                clearInvocations(repositoryImpl)
            }
        }
    }
}