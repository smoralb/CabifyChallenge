package com.smb.ft_checkout.domain.di

import com.smb.ft_checkout.domain.usecase.GetShoppingCartItemsUseCase
import com.smb.ft_checkout.domain.usecase.GetShoppingCartItemsUseCaseImpl
import org.koin.dsl.module

val checkoutDomainModule = module {
    factory<GetShoppingCartItemsUseCase> { GetShoppingCartItemsUseCaseImpl(repository = get()) }
}