package com.smb.ft_store.ui.di

import com.smb.ft_store.ui.detail.DetailViewModel
import com.smb.ft_store.ui.store.StoreViewModel
import com.smb.ft_store.ui.store.mapper.StoreUiMapper
import com.smb.ft_store.ui.store.mapper.StoreUiMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val storePresentationModule = module {

    factory<StoreUiMapper> { StoreUiMapperImpl() }

    viewModel { StoreViewModel(get(), get()) }
    viewModel { DetailViewModel(get()) }
}