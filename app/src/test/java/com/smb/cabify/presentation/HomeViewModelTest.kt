package com.smb.cabify.presentation

import com.smb.cabify.domain.usecases.GetSampleDataUseCase
import com.smb.cabify.presentation.main.home.HomeViewModel
import com.smb.cabify.presentation.main.home.HomeViewState.HideLoading
import com.smb.cabify.presentation.main.home.mapper.FirstFragmentMapper
import com.smb.cabify.presentation.mocks.sampleResponseChildModelMock
import com.smb.core.data.Result
import com.smb.core.test.BaseViewModelUnitTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class HomeViewModelTest : BaseViewModelUnitTest() {

    @Mock
    private lateinit var getSampleDataUseCase: GetSampleDataUseCase

    @Mock
    private lateinit var mapper: FirstFragmentMapper

    private lateinit var viewModel: HomeViewModel

    @BeforeEach
    fun setUp() {
        viewModel = HomeViewModel(getSampleDataUseCase, mapper)
    }

    @TestFactory
    fun `getSampleData should return sample data `() = listOf(
        Result.Success(sampleResponseChildModelMock),
        Result.Error()
    ).map { testCase ->
        DynamicTest.dynamicTest("$testCase") {
            runBlockingTest {
                whenever(getSampleDataUseCase(any())).thenReturn(testCase)
                whenever(mapper.mapItems(any(), (any()))).thenReturn(emptyList())

                viewModel.initialize()

                when (testCase.isSuccess) {
                    true -> {
                        verify(mapper).mapItems(any(), any())
                        assertEquals(HideLoading, viewModel.viewState.value)
                    }
                    else -> assertEquals(viewModel.firstViewModelText.value, "Error")
                }
            }
            assertTrue(viewModel.viewState.value == HideLoading)
            clearInvocations(getSampleDataUseCase, mapper)
        }
    }

}