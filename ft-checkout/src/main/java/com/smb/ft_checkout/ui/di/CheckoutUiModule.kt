package com.smb.ft_checkout.ui.di

import com.smb.ft_checkout.ui.CheckoutUiMapper
import com.smb.ft_checkout.ui.CheckoutUiMapperImpl
import org.koin.dsl.module

val checkoutUiModule = module {

    factory<CheckoutUiMapper> { CheckoutUiMapperImpl() }
}