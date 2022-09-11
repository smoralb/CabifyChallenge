package com.smb.ft_store.data.source

import com.smb.ft_store.data.entity.ProductEntity
import com.smb.ft_store.data.entity.ProductType.TSHIRT
import com.smb.ft_store.data.entity.ProductType.VOUCHER
import com.smb.ft_store.data.entity.ProductsListEntity
import com.smb.ft_store.data.productDataModelMugMock
import com.smb.ft_store.data.productDataModelTshirtMock
import com.smb.ft_store.data.productDataModelVoucherMock
import com.smb.ft_store.data.productsDataEntityMock
import com.smb.ft_store.data.source.remote.mapper.StoreDataMapper
import com.smb.ft_store.data.source.remote.mapper.StoreDataMapperImpl
import com.smb.ft_store.domain.model.StoreProductModel
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
    fun `toDomainModel should return list of domain models`() =
        listOf<Pair<List<ProductEntity>, StoreProductModel>>(
            listOf(productsDataEntityMock) to productDataModelMugMock,
            listOf(productsDataEntityMock.copy(code = VOUCHER)) to productDataModelVoucherMock,
            listOf(productsDataEntityMock.copy(code = TSHIRT)) to productDataModelTshirtMock
        ).map { testCase ->
            DynamicTest.dynamicTest(" $testCase") {
                val result = mapper.toDomainModel(
                    ProductsListEntity(products = testCase.first)
                )
                assertEquals(result.first(), testCase.second)
            }
        }
}