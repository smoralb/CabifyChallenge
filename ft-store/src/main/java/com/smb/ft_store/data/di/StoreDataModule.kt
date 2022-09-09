package com.smb.ft_store.data.di

import com.smb.ft_store.data.repository.StoreRepositoryImpl
import com.smb.ft_store.data.source.remote.mapper.StoreDataMapper
import com.smb.ft_store.data.source.remote.mapper.StoreDataMapperImpl
import com.smb.ft_store.data.service.StoreApi
import com.smb.ft_store.data.source.remote.StoreRemoteSource
import com.smb.ft_store.data.source.remote.StoreRemoteSourceImpl
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

    single<StoreRepository> { StoreRepositoryImpl(get(), get()) }

    single { get<Retrofit>().create(StoreApi::class.java) }

}