package com.hfad.wetherwithmvvm.di

import com.hfad.wetherwithmvvm.framework.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    //View models
    viewModel { MainViewModel() }
}