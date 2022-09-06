package com.smb.core.domain.dataStore.di

import com.smb.core.data.dataStore.repository.CheckoutRepositoryImpl
import com.smb.core.domain.dataStore.repository.CheckoutRepository
import org.koin.dsl.module

val dataStoreDomainModule = module {
    factory<CheckoutRepository> { CheckoutRepositoryImpl(localSource = get()) }
}