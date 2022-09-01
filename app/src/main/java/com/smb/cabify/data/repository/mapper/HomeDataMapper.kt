package com.smb.cabify.data.repository.mapper

import com.smb.cabify.data.entity.ProductsListEntity
import com.smb.cabify.domain.model.ProductModel
import com.smb.core.extensions.orDefault

interface HomeDataMapper {

    fun toDomainModel(entity: ProductsListEntity): List<ProductModel>
}

class HomeDataMapperImpl : HomeDataMapper {

    override fun toDomainModel(entity: ProductsListEntity): List<ProductModel> =
        entity.products?.map { product ->
            ProductModel(
                code = product.code.orEmpty(),
                name = product.name.orEmpty(),
                price = product.price.orDefault()
            )
        }.orEmpty()

}