package com.smb.ft_checkout.domain.di

import com.smb.ft_checkout.data.repository.CheckoutRepositoryImpl
import com.smb.ft_checkout.domain.repository.CheckoutRepository
import org.koin.dsl.module

val checkoutDomainModule = module {
    factory<CheckoutRepository> { CheckoutRepositoryImpl(localSource = get()) }
}