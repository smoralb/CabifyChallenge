package com.smb.cabify.domain.di

import com.smb.cabify.domain.usecases.GetSampleDataUseCase
import com.smb.cabify.domain.usecases.GetSampleDataUseCaseImpl
import org.koin.dsl.module

val baseDomainModule = module {

    factory<GetSampleDataUseCase> { GetSampleDataUseCaseImpl(get()) }
}