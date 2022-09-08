package com.smb.ft_store.ui.di

import com.smb.ft_store.ui.detail.DetailViewModel
import com.smb.ft_store.ui.detail.mapper.DetailUiMapper
import com.smb.ft_store.ui.detail.mapper.DetailUiMapperImpl
import com.smb.ft_store.ui.store.StoreViewModel
import com.smb.ft_store.ui.store.mapper.StoreUiMapper
import com.smb.ft_store.ui.store.mapper.StoreUiMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val storePresentationModule = module {

    factory<StoreUiMapper> { StoreUiMapperImpl() }
    factory<DetailUiMapper> { DetailUiMapperImpl(get()) }

    viewModel { StoreViewModel(get(), get(), get()) }
    viewModel { DetailViewModel(get(), get()) }
}