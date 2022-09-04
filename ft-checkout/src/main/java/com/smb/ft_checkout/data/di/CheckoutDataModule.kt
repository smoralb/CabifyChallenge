package com.smb.ft_checkout.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.smb.ft_checkout.ShoppingCart
import com.smb.ft_checkout.data.proto.Serializer
import com.smb.ft_checkout.data.source.CheckoutLocalSource
import com.smb.ft_checkout.data.source.CheckoutLocalSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

const val DATA_STORE_FILE_NAME = "cart.db"

val checkoutDataModule = module {
    single<DataStore<ShoppingCart>> {
        DataStoreFactory.create(
            serializer = Serializer,
            produceFile = { androidContext().dataStoreFile(DATA_STORE_FILE_NAME) },
            corruptionHandler = null
        )
    }
    single<CheckoutLocalSource> { CheckoutLocalSourceImpl(dataStore = get()) }
}