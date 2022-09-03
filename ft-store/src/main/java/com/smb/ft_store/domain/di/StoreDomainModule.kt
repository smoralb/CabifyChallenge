package com.smb.ft_store.domain.di

import com.smb.ft_store.domain.usecase.GetProductListUseCase
import com.smb.ft_store.domain.usecase.GetProductListUseCaseImpl
import org.koin.dsl.module

val storeDomainModule = module {

    factory<GetProductListUseCase> { GetProductListUseCaseImpl(get()) }
}