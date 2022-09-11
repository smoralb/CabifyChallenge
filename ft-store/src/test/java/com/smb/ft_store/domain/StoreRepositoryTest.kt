package com.smb.ft_store.domain

import com.smb.core.data.source.LocalSource
import com.smb.core.extensions.EMPTY_STRING
import com.smb.core.test.BaseUnitTest
import com.smb.ft_store.data.repository.StoreRepositoryImpl
import com.smb.ft_store.data.source.remote.StoreRemoteSource
import com.smb.ft_store.domain.repository.StoreRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class StoreRepositoryTest : BaseUnitTest() {

    @Mock
    private lateinit var localSource: LocalSource

    @Mock
    private lateinit var remoteSource: StoreRemoteSource

    private lateinit var repository: StoreRepository

    @BeforeEach
    fun setUp() {
        repository = StoreRepositoryImpl(localSource, remoteSource)
    }

    @Test
    fun `getProductList should call remote source to get item list`() {
        runBlockingTest {
            repository.getProductList()
            verify(remoteSource).getProductList()
        }
    }

    @Test
    fun `getProductDetails should call remote source to get item details`() {
        runBlockingTest {
            repository.getProductDetails(EMPTY_STRING)
            verify(remoteSource).getProductDetails(EMPTY_STRING)
        }
    }

    @Test
    fun `addNewItem should call local source to add new item`() {
        runBlockingTest {
            repository.addNewItem(productRequestDomainMock)
            verify(localSource).addNewItem(productRequestDomainMock)
        }
    }

}