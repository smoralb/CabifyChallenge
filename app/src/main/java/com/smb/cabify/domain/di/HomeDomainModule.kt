package com.smb.cabify.domain.di

import com.smb.cabify.domain.usecases.GetProductListUseCase
import com.smb.cabify.domain.usecases.GetProductListUseCaseImpl
import org.koin.dsl.module

val homeDomainModule = module {

    factory<GetProductListUseCase> { GetProductListUseCaseImpl(get()) }
}