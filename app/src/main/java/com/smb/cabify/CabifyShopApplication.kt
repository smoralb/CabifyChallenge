package com.smb.cabify

import android.app.Application
import com.smb.core.BuildConfig
import com.smb.core.data.di.baseDataModule
import com.smb.ft_checkout.domain.di.checkoutDomainModule
import com.smb.ft_checkout.ui.di.checkoutUiModule
import com.smb.ft_navigation.di.navigatorModule
import com.smb.ft_store.data.di.storeDataModule
import com.smb.ft_store.ui.di.storePresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CabifyShopApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@CabifyShopApplication)
            modules(
                listOf(
                    storePresentationModule,
                    baseDataModule,
                    storeDataModule,
                    navigatorModule,
                    checkoutDomainModule,
                    checkoutUiModule
                )
            )
        }
    }
}