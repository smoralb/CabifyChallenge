package com.smb.cabify.data.di

import com.smb.cabify.data.HomeApi
import com.smb.cabify.data.repository.HomeRepositoryImpl
import com.smb.cabify.data.repository.mapper.HomeDataMapper
import com.smb.cabify.data.repository.mapper.HomeDataMapperImpl
import com.smb.cabify.data.source.HomeRemoteSource
import com.smb.cabify.data.source.HomeRemoteSourceImpl
import com.smb.cabify.domain.repository.HomeRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val homeDataModule = module {

    single<HomeDataMapper> { HomeDataMapperImpl() }

    single<HomeRemoteSource> { HomeRemoteSourceImpl(get(), get()) }

    single<HomeRepository> { HomeRepositoryImpl(get()) }

    single { get<Retrofit>().create(HomeApi::class.java) }

}