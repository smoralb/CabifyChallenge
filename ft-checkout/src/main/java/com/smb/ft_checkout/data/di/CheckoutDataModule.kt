package com.smb.ft_checkout.data.di

import com.smb.ft_checkout.data.repository.CheckoutRepositoryImpl
import com.smb.ft_checkout.data.source.CheckoutLocalSource
import com.smb.ft_checkout.data.source.CheckoutLocalSourceImpl
import com.smb.ft_checkout.domain.repository.CheckoutRepository
import org.koin.dsl.module

val checkoutDataModule = module {
    single<CheckoutLocalSource> { CheckoutLocalSourceImpl() }
    single<CheckoutRepository> { CheckoutRepositoryImpl(localSource = get()) }
}