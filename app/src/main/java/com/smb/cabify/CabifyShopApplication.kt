package com.smb.cabify

import android.app.Application
import com.smb.cabify.data.di.homeDataModule
import com.smb.cabify.domain.di.homeDomainModule
import com.smb.cabify.presentation.di.homePresentationModule
import com.smb.core.data.di.baseDataModule
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
                    homePresentationModule,
                    baseDataModule,
                    homeDataModule,
                    homeDomainModule
                )
            )
        }
    }
}