package com.smb.cabify.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductModel(
    val code: String,
    val name: String,
    val price: Float,
    val image: String
) : Parcelable