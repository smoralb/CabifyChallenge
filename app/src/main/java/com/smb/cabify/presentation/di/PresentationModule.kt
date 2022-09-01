package com.smb.cabify.presentation.di

import com.smb.cabify.presentation.main.home.HomeViewModel
import com.smb.cabify.presentation.main.home.mapper.FirstFragmentMapper
import com.smb.cabify.presentation.main.home.mapper.FirstFragmentMapperImpl
import com.smb.cabify.presentation.main.secondView.SecondViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    factory<FirstFragmentMapper> { FirstFragmentMapperImpl() }

    viewModel { HomeViewModel(get(), get()) }
    viewModel { SecondViewModel(get()) }
}