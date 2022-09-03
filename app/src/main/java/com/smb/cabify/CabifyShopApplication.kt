package com.smb.cabify

import android.app.Application
import com.smb.core.data.di.baseDataModule
import com.smb.ft_store.data.di.storeDataModule
import com.smb.ft_store.domain.di.storeDomainModule
import com.smb.ft_store.ui.di.storePresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CabifyShopApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CabifyShopApplication)
            modules(
                listOf(
                    storePresentationModule,
                    baseDataModule,
                    storeDataModule,
                    storeDomainModule
                )
            )
        }
    }
}