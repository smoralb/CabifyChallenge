package com.smb.cabify

import android.app.Application
import com.smb.cabify.data.di.sampleDataModule
import com.smb.cabify.domain.di.baseDomainModule
import com.smb.cabify.presentation.di.presentationModule
import com.smb.core.data.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CabifyShop: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@CabifyShop)
            modules(listOf(presentationModule, dataModule, sampleDataModule, baseDomainModule))
        }
    }
}