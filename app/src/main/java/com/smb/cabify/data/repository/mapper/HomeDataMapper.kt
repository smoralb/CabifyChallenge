package com.smb.cabify.data.repository.mapper

import com.smb.cabify.data.entity.ProductType
import com.smb.cabify.data.entity.ProductType.MUG
import com.smb.cabify.data.entity.ProductType.TSHIRT
import com.smb.cabify.data.entity.ProductType.VOUCHER
import com.smb.cabify.data.entity.ProductsListEntity
import com.smb.cabify.domain.model.ProductModel
import com.smb.core.extensions.orDefault

private const val VOUCHER_IMAGE_URL =
    "https://images.unsplash.com/photo-1494426383302-7b9d36a1a028?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1738&q=80"
private const val T_SHIRT_IMAGE_URL =
    "https://images.unsplash.com/photo-1529374255404-311a2a4f1fd9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1738&q=80"
private const val MUG_IMAGE_URL =
    "https://images.unsplash.com/photo-1516390118834-21602d501886?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1836&q=80"
private const val DEFAULT_IMAGE =
    "https://upload.wikimedia.org/wikipedia/commons/5/58/Cabify-logo-purple.png"

interface HomeDataMapper {

    fun toDomainModel(entity: ProductsListEntity): List<ProductModel>
}

class HomeDataMapperImpl : HomeDataMapper {

    override fun toDomainModel(entity: ProductsListEntity): List<ProductModel> =
        entity.products?.map { product ->
            ProductModel(
                code = product.code?.value.orEmpty(),
                name = product.name.orEmpty(),
                price = product.price.orDefault(),
                image = getFakeImage(product.code)
            )
        }.orEmpty()

    private fun getFakeImage(code: ProductType?): String =
        when (code) {
            VOUCHER -> VOUCHER_IMAGE_URL
            TSHIRT -> T_SHIRT_IMAGE_URL
            MUG -> MUG_IMAGE_URL
            else -> DEFAULT_IMAGE
        }
}