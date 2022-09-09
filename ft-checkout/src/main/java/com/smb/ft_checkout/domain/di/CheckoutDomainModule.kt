package com.smb.ft_checkout.domain.di

import com.smb.ft_checkout.data.CheckoutRepositoryImpl
import com.smb.ft_checkout.domain.CheckoutRepository
import org.koin.dsl.module

val checkoutDomainModule = module {
    single<CheckoutRepository> { CheckoutRepositoryImpl(localSource = get()) }
}