package com.smb.ft_checkout.ui.di

import com.smb.ft_checkout.ui.CheckoutUiMapper
import com.smb.ft_checkout.ui.CheckoutUiMapperImpl
import com.smb.ft_checkout.ui.CheckoutViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val checkoutUiModule = module {

    factory<CheckoutUiMapper> { CheckoutUiMapperImpl(context = androidContext()) }

    factory { CheckoutViewModel(mapper = get(), repository = get(), navigator = get()) }
}