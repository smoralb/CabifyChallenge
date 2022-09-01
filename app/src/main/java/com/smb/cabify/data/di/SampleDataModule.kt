package com.smb.cabify.data.di

import com.smb.cabify.data.SampleApi
import com.smb.cabify.domain.repository.SampleDataRepository
import com.smb.cabify.data.repository.SampleDataRepositoryImpl
import com.smb.cabify.data.repository.mapper.SampleDataMapper
import com.smb.cabify.data.repository.mapper.SampleDataMapperImpl
import com.smb.cabify.data.source.SampleDataRemoteSource
import com.smb.cabify.data.source.SampleDataRemoteSourceImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val sampleDataModule = module {

    single<SampleDataMapper> { SampleDataMapperImpl() }

    single<SampleDataRemoteSource> { SampleDataRemoteSourceImpl(get(), get()) }

    single<SampleDataRepository> { SampleDataRepositoryImpl(get()) }

    single { get<Retrofit>().create(SampleApi::class.java) }

}