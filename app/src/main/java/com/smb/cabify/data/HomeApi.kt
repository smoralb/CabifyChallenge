package com.smb.cabify.data

import com.smb.cabify.data.entity.ProductsListEntity
import retrofit2.Response
import retrofit2.http.GET

interface HomeApi {

    @GET("/palcalde/6c19259bd32dd6aafa327fa557859c2f/raw/ba51779474a150ee4367cda4f4ffacdcca479887/Products.json")
    suspend fun getProductList(): Response<ProductsListEntity>

}