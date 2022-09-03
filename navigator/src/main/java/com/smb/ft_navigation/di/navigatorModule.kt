package com.smb.ft_navigation.di

import com.smb.ft_navigation.StoreNavigatorImpl
import com.smb.ft_store.ui.navigation.StoreNavigator
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val navigatorModule = module {

    single<StoreNavigator> { StoreNavigatorImpl(androidContext()) }

}