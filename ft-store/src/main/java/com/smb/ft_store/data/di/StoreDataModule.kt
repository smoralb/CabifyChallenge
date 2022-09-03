package com.smb.ft_store.data.di

import com.smb.ft_store.data.repository.StoreRepositoryImpl
import com.smb.ft_store.data.repository.StoreDataMapper
import com.smb.ft_store.data.repository.StoreDataMapperImpl
import com.smb.ft_store.data.service.StoreApi
import com.smb.ft_store.data.source.StoreRemoteSource
import com.smb.ft_store.data.source.StoreRemoteSourceImpl
import com.smb.ft_store.domain.repository.StoreRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val storeDataModule = module {

    single<StoreDataMapper> { StoreDataMapperImpl() }

    single<StoreRemoteSource> {
        StoreRemoteSourceImpl(
            get(),
            get()
        )
    }

    single<StoreRepository> { StoreRepositoryImpl(get()) }

    single { get<Retrofit>().create(StoreApi::class.java) }

}