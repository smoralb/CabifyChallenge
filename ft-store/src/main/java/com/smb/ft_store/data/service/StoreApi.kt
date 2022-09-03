package com.smb.ft_store.data.service

import com.smb.ft_store.data.entity.ProductsListEntity
import retrofit2.Response
import retrofit2.http.GET

interface StoreApi {

    @GET("/palcalde/6c19259bd32dd6aafa327fa557859c2f/raw/ba51779474a150ee4367cda4f4ffacdcca479887/Products.json")
    suspend fun getProductList(): Response<ProductsListEntity>
}