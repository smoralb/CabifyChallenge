package com.smb.ft_checkout.data.proto

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.smb.ft_checkout.ShoppingCart
import java.io.InputStream
import java.io.OutputStream

object Serializer : Serializer<ShoppingCart> {
    override val defaultValue: ShoppingCart = ShoppingCart.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ShoppingCart {
        try {
            return ShoppingCart.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: ShoppingCart, output: OutputStream) = t.writeTo(output)
}