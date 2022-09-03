package com.smb.cabify.presentation.di

import com.smb.cabify.presentation.home.HomeViewModel
import com.smb.cabify.presentation.home.mapper.HomePresentationMapper
import com.smb.cabify.presentation.home.mapper.HomePresentationMapperImpl
import com.smb.cabify.presentation.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homePresentationModule = module {

    factory<HomePresentationMapper> { HomePresentationMapperImpl() }

    viewModel { HomeViewModel(get(), get()) }
    viewModel { DetailViewModel() }
}