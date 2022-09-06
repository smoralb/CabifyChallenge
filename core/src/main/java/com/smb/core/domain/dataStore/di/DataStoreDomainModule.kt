package com.smb.core.domain.dataStore.di

import com.smb.core.data.dataStore.repository.LocalRepositoryImpl
import com.smb.core.domain.dataStore.repository.LocalRepository
import org.koin.dsl.module

val dataStoreDomainModule = module {
    factory<LocalRepository> { LocalRepositoryImpl(localSource = get()) }
}