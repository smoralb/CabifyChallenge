package com.smb.ft_store.data.source.remote.mapper

import com.smb.core.extensions.orDefault
import com.smb.ft_store.data.entity.ProductType
import com.smb.ft_store.data.entity.ProductType.MUG
import com.smb.ft_store.data.entity.ProductType.TSHIRT
import com.smb.ft_store.data.entity.ProductType.VOUCHER
import com.smb.ft_store.data.entity.ProductsListEntity
import com.smb.ft_store.domain.model.DiscountType
import com.smb.ft_store.domain.model.DiscountType.DISCOUNT_2_X_1
import com.smb.ft_store.domain.model.DiscountType.DISCOUNT_BULK_PURCHASE
import com.smb.ft_store.domain.model.DiscountType.NO_DISCOUNT
import com.smb.ft_store.domain.model.StoreProductModel

private const val VOUCHER_IMAGE_URL =
    "https://images.unsplash.com/photo-1494426383302-7b9d36a1a028?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1738&q=80"
private const val T_SHIRT_IMAGE_URL =
    "https://images.unsplash.com/photo-1529374255404-311a2a4f1fd9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1738&q=80"
private const val MUG_IMAGE_URL =
    "https://images.unsplash.com/photo-1516390118834-21602d501886?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1836&q=80"
private const val DEFAULT_IMAGE =
    "https://upload.wikimedia.org/wikipedia/commons/5/58/Cabify-logo-purple.png"
private const val FAKE_DESCRIPTION =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"

interface StoreDataMapper {

    fun toDomainModel(entity: ProductsListEntity): List<StoreProductModel>

    fun toDomainModelDetails(productId: String, entity: ProductsListEntity): StoreProductModel
}

class StoreDataMapperImpl : StoreDataMapper {

    val discountType: (ProductType?) -> DiscountType = {
        when (it) {
            VOUCHER -> DISCOUNT_2_X_1
            TSHIRT -> DISCOUNT_BULK_PURCHASE
            else -> NO_DISCOUNT
        }
    }

    override fun toDomainModel(entity: ProductsListEntity): List<StoreProductModel> =
        entity.products?.map { product ->
            StoreProductModel(
                id = product.code,
                name = product.name.orEmpty(),
                description = FAKE_DESCRIPTION,
                price = product.price.orDefault(),
                image = getFakeImage(product.code),
                hasDiscount = hasDiscount(product.code),
                discountType = discountType(product.code)
            )
        }.orEmpty()

    override fun toDomainModelDetails(
        productId: String,
        entity: ProductsListEntity
    ): StoreProductModel {
        val product = entity.products?.first { it.code?.value == productId }
        return StoreProductModel(
            id = product?.code,
            name = product?.name.orEmpty(),
            description = FAKE_DESCRIPTION,
            price = product?.price.orDefault(),
            image = getFakeImage(product?.code),
            hasDiscount = hasDiscount(product?.code),
            discountType = discountType(product?.code)
        )
    }

    private fun hasDiscount(productType: ProductType?) =
        when (productType) {
            VOUCHER, TSHIRT -> true
            else -> false
        }

    private fun getFakeImage(code: ProductType?): String =
        when (code) {
            VOUCHER -> VOUCHER_IMAGE_URL
            TSHIRT -> T_SHIRT_IMAGE_URL
            MUG -> MUG_IMAGE_URL
            else -> DEFAULT_IMAGE
        }
}