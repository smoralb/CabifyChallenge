package com.smb.cabify.presentation.di

import com.smb.cabify.presentation.main.firstView.FirstViewModel
import com.smb.cabify.presentation.main.firstView.mapper.FirstFragmentMapper
import com.smb.cabify.presentation.main.firstView.mapper.FirstFragmentMapperImpl
import com.smb.cabify.presentation.main.secondView.SecondViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    factory<FirstFragmentMapper> { FirstFragmentMapperImpl() }

    viewModel { FirstViewModel(get(), get()) }
    viewModel { SecondViewModel(get()) }
}